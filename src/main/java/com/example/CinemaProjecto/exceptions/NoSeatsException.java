package com.example.CinemaProjecto.exceptions;

import lombok.Data;

@Data
public class NoSeatsException extends RuntimeException {
    public NoSeatsException(String message) {
        super(message);
    }
}
