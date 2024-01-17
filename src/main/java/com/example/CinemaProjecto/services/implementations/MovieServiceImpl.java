package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.advice.NotFoundException;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.Genre;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;
    @Override
    public List<MovieDto> getAll() {
        return repository.findAll().stream().map(m ->
                new MovieDto(
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
        Movie m = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Movie not found"));
        return new MovieDto(
                m.getTitle(),
                m.getDescription(),
                m.getGenres().stream().map(Genre::getName).toList(),
                null,
//                m.getCinemas().stream().map(Cinema::getName).toList()
                m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
        );
    }

    @Override
    public List<MovieDto> getMoviesByName(String name) {
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        return repository.findByGenre(genre).stream().map(m ->
                new MovieDto(
                        m.getTitle(),
                        m.getDescription(),
                        m.getGenres().stream().map(Genre::getName).toList(),
                        null,
//                        m.getCinemas().stream().map(Cinema::getName).toList()
                        m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
                )).toList();
    }

    @Override
    public List<MovieDto> getMoviesByCinema(String cinema) {
        return repository.findByCinema(cinema).stream().map(m ->
            new MovieDto(m.getTitle(), m.getDescription(),
                m.getGenres().stream().map(Genre::getName).toList(),
                null,   ///todo pune actorii
//                m.getCinemas().stream().map(Cinema::getName).toList()
                m.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
            )
        ).toList();
    }
}
