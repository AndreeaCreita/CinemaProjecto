package com.example.CinemaProjecto.services.implementations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.repositories.CinemaMovieRepository;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final CinemaMovieRepository cinemaMovieRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public Page<MovieDto> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable).map(m -> new MovieDto(
                m.getId(),
                m.getTitle(),
                m.getDescription(),
                m.getGenres() != null ? m.getGenres().stream().map(Genre::getName).toList() : List.of(),
                m.getActors() != null ? m.getActors().stream().map(Actor::getName).toList() : List.of(),
                m.getCinemaMovies() != null ? m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList() : List.of()
        ));
    }

    @Override
    public MovieDto getById(Long id) {
        Movie m = movieRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie not found"));
        log.info("Get movie from repository, movie id: " + m.getId());
        return new MovieDto(
                m.getId(),
                m.getTitle(),
                m.getDescription(),
                m.getGenres().stream().map(Genre::getName).toList(),
                m.getActors().stream().map(Actor::getName).toList(),
                m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
        );
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre).stream().map(m -> {

            log.info("Processing movie for genre '" + genre + "', movie ID: " + m.getId() + ", movie title: " + m.getTitle());
            return new MovieDto(
                    m.getId(),
                    m.getTitle(),
                    m.getDescription(),
                    m.getGenres().stream().map(Genre::getName).toList(),
                    m.getActors().stream().map(Actor::getName).toList(),
                    m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
            );
        }).toList();
    }



    @Override
    public List<MovieDto> getMoviesByCinema(String cinemaName) {
        var cinema = cinemaRepository.findByName(cinemaName);
        if (cinema == null) {
            throw new NotFoundException("Cinema not found");
        }
        return cinema.getCinemaMovies().stream().map(cm -> {
            var movie = cm.getMovie();
            var movieCinemas = cinemaMovieRepository.findByMovieId(movie.getId())
                    .stream().map(Cinema::getName).toList();
            log.info("Processing movie for cinema '" + cinemaName + "', movie ID: " + movie.getId() + ", movie title: " + movie.getTitle());
            return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenres().stream().map(Genre::getName).toList(),
                    movie.getActors().stream().map(Actor::getName).toList(), movieCinemas);
        }).toList();
    }


}
