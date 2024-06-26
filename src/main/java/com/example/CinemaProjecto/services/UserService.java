package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.UserDto;
import com.example.CinemaProjecto.dtos.UserLoginDto;
import com.example.CinemaProjecto.dtos.UserRegisterDto;
import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    public UserDto getUser(Long userId);
    public UserDto loginUser(UserLoginDto userLoginDto);
    boolean registerUser(UserRegisterDto userRegisterDto);
}
