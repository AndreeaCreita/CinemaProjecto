package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.dtos.UserDto;
import com.example.CinemaProjecto.dtos.UserLoginDto;
import com.example.CinemaProjecto.dtos.UserRegisterDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.UserRepository;
import com.example.CinemaProjecto.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        log.info("Fetched user from repository, user id: " + user.getId());
        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getTickets().stream().map(t ->
                        new TicketDto(t.getId(), t.getUser().getEmail(), t.getMovie().getTitle(), t.getCinema().getName(), t.getDateTime())).toList());
    }

    @Override
    public UserDto loginUser(UserLoginDto userLoginDto) {
        var userOptional = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if(userOptional.isPresent())
        {
            var user = userOptional.get();
            return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                    user.getTickets().stream().map(t ->
                            new TicketDto(t.getId(), t.getUser().getEmail(), t.getMovie().getTitle(), t.getCinema().getName(), t.getDateTime())).toList());
        }
        return null;
    }

    @Override
    public boolean registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            return false; // Email already in use, return false
        }
        User newUser = new User();
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setFirstName(userRegisterDto.getFirstName());
        newUser.setLastName(userRegisterDto.getLastName());
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword())); // Hash the password
        userRepository.save(newUser);
        return true; // Successful registration, return true
    }

}
