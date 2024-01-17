package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.repositories.CinemaMovieRepository;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final CinemaMovieRepository cinemaMovieRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public List<MovieDto> getAll() {
        return movieRepository.findAll().stream().map(m ->
                new MovieDto(
                        m.getId(),
                        m.getTitle(),
                        m.getDescription(),
                        m.getGenres().stream().map(Genre::getName).toList(),
                        null,   //todo georgian sa rezolvi aici
//                        m.getCinemas().stream().map(Cinema::getName).toList()
                        m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
                )).toList();

    }

    @Override
    public MovieDto getById(Long id) {
        Movie m = movieRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie not found"));
        return new MovieDto(
                m.getId(),
                m.getTitle(),
                m.getDescription(),
                m.getGenres().stream().map(Genre::getName).toList(),
                null,
                m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
        );
    }

    @Override
    public List<MovieDto> getMoviesByName(String name) {
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre).stream().map(m ->
                new MovieDto(
                        m.getId(),
                        m.getTitle(),
                        m.getDescription(),
                        m.getGenres().stream().map(Genre::getName).toList(),
                        null,
                        m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
                )).toList();
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
            return new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenres().stream().map(Genre::getName).toList(),
                    movie.getActors().stream().map(Actor::getName).toList(), movieCinemas);
        }).toList();
    }
}
