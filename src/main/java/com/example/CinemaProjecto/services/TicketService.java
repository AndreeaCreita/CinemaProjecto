package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    TicketDto createTicket(RequestCreateTicketDto requestCreateTicketDto);
    void cancelTicket(Long ticketId);
    TicketDto updateTicketDate(Long ticketId, LocalDateTime time);

    List<TicketDto> getAllTickets();
    TicketDto getTicketById(Long ticketId);
}
