package com.example.CinemaProjecto.exceptions;

public class TicketNotCancellable extends RuntimeException {
    public TicketNotCancellable(String message) {
        super(message);
    }
}
