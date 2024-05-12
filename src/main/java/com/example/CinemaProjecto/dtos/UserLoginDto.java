package com.example.CinemaProjecto.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
