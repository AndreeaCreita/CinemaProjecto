package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;

import java.time.LocalDateTime;

public interface TicketService {
    TicketDto createTicket(RequestCreateTicketDto requestCreateTicketDto);
    void cancelTicket(Long ticketId);
    TicketDto updateTicketDate(Long ticketId, LocalDateTime time);
}
