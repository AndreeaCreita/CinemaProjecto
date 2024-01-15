package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService service;

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDto>> getByGenre(
        @PathVariable String genre
    ) {
        return new ResponseEntity<>(service.getMoviesByGenre(genre), HttpStatus.OK);
    }
}
