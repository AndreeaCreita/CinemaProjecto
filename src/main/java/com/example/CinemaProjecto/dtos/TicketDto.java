package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketDto {
    private Long id;

    private String userEmail;

    private String movieTitle;

    private String cinemaName;

    private LocalDateTime dateTime;
}
