package com.example.CinemaProjecto.dtos;

import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TicketDto {
    private Long id;

    private String userEmail;

    private String movieName;

    private String cinemaName;

    private LocalDateTime dateTime;
}
