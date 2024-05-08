package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Page<MovieDto> getAll(Pageable pageable);
    MovieDto getById(Long id);
    List<MovieDto> getMoviesByGenre(String genre);
    List<MovieDto> getMoviesByCinema(String cinema);
}
