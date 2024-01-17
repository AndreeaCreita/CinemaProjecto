package com.example.CinemaProjecto.dtos;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
