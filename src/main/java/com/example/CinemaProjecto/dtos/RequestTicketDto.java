package com.example.CinemaProjecto.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class RequestTicketDto {
    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long movieId;

    @NotNull
    @Min(1)
    private Long cinemaId;

    @NotNull
    @Future
    private LocalDateTime movieTime;
}
