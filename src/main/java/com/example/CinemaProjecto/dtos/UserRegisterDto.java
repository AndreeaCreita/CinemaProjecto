package com.example.CinemaProjecto.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    // Optionally, if you want to confirm password during registration
    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;

    // Default constructor for frameworks
    public UserRegisterDto() {
    }

    // Full constructor for all fields
    public UserRegisterDto(String email, String password, String firstName, String lastName, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.confirmPassword = confirmPassword;
    }
}