package com.example.CinemaProjecto.dtos;

import com.example.CinemaProjecto.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private List<TicketDto> tickets;

}
