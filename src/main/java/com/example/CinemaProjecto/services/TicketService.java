package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.RequestTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;

public interface TicketService {
    TicketDto createTicket(RequestTicketDto requestTicketDto);
    void cancelTicket(Long ticketId);
}
