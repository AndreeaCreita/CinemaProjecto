package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired //dependecy injections
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return user;


    }
}
