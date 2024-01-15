package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CinemaDto {
    private Long id;

    private String name;

    private String location;
}
