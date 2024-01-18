package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestCreateTicketDto {
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
