package com.example.CinemaProjecto.dtos;

import lombok.Data;

@Data
public class RequestTicketDto {
    private Long userId;
    private Long movieId;
    private Long cinemaId;
}
