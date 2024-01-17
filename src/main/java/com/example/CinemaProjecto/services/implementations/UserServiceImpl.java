package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.dtos.UserDto;
import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.UserRepository;
import com.example.CinemaProjecto.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDto getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getTickets().stream().map(t ->
                        new TicketDto(t.getId(), t.getUser().getEmail(), t.getMovie().getTitle(), t.getCinema().getName(), t.getDateTime())).toList());
    }
}
