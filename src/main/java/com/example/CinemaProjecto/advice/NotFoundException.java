package com.example.CinemaProjecto.advice;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
