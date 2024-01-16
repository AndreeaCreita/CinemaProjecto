package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAll();
    MovieDto getById(Long id);
    List<MovieDto> getMoviesByName(String name);
    List<MovieDto> getMoviesByGenre(String genre);
    List<MovieDto> getMoviesByCinema(String cinema);
}
