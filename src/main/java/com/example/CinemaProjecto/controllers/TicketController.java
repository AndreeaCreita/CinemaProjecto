package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.RequestTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @PostMapping("/buy")
    public ResponseEntity<TicketDto> buyTicket(
        @Valid @RequestBody RequestTicketDto requestTicketDto
    ) {
        return new ResponseEntity<>(service.createTicket(requestTicketDto), HttpStatus.OK);
    }
}
