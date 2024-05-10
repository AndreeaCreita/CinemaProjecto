package com.example.CinemaProjecto.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestUpdateTicketDto {
    @NotNull
    @Min(1)
    private Long ticketId;

    @NotNull
    @Future
    private LocalDateTime movieTime;
}
