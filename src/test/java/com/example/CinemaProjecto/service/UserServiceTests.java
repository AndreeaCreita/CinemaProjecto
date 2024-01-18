package com.example.CinemaProjecto.service;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.repositories.UserRepository;
import com.example.CinemaProjecto.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("UserService unit test:given user id throw NotFoundException")
    public void givenUserId_getUser_throwNotFoundException() {
        given(userRepository.findById(ArgumentMatchers.anyLong()))
                .willThrow(new NotFoundException("User not found"));

        assertThrows(NotFoundException.class,
                () -> service.getUser(1L),
                "User not found");
    }

}
