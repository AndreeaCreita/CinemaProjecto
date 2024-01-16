package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieDto {
    private String title;
    private String description;
    private List<String> genres;
    private List<String> actors;
    private List<String> cinemas;
}
