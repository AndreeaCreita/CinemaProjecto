package com.example.CinemaProjecto.dtos;

import com.example.CinemaProjecto.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActorDto {
    private Long id;

    private String name;

    private List<String> movies;
}
