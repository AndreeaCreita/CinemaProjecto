package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.RequestTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.repositories.TicketRepository;
import com.example.CinemaProjecto.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    @Override
    public TicketDto createTicket(RequestTicketDto requestTicketDto) {
        return null;
    }
}
