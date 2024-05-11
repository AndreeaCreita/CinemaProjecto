package com.example.CinemaProjecto.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateTicketDto {
    @NotNull
    @Min(1)
    private Long ticketId;

    @NotNull
    @Future
    private LocalDateTime movieTime;
}
