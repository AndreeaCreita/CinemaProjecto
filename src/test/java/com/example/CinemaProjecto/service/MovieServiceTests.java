package com.example.CinemaProjecto.service;

import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.repositories.CinemaMovieRepository;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.implementations.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.BDDMockito.given;
@ActiveProfiles("h2")
@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {

    @InjectMocks
    private MovieServiceImpl service;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CinemaMovieRepository cinemaMovieRepository;
    @Mock
    private CinemaRepository cinemaRepository;

    private Movie movie;

    @BeforeEach
    public void before() {
        movie = new Movie(
            1L, "The Godfather", "Vito Corleone is the head of the Corleone mafia family",
            null, new ArrayList<Actor>(),
                new ArrayList<Ticket>(), new ArrayList<CinemaMovie>()
        );
        movie.setGenres(List.of(new Genre(1L, "mafia", List.of(movie))));
    }

    @Test
    @DisplayName("MovieService unit test: return all movies")
    public void getALL_returnListOfMovies() {
        Movie movie = new Movie();
        movie.setTitle("Example Movie");
        movie.setId(1L);

        List<Movie> allMovies = List.of(movie);
        Page<Movie> pagedResponse = new PageImpl<>(allMovies);

        Pageable pageable = PageRequest.of(0, 10);
        given(movieRepository.findAll(pageable)).willReturn(pagedResponse);


        Page<MovieDto> movies = service.getAll(pageable);


        assertEquals(1, movies.getTotalElements());
        assertEquals(movie.getTitle(), movies.getContent().get(0).getTitle());
        assertEquals(movie.getId(), movies.getContent().get(0).getId());
    }

    @Test
    @DisplayName("MovieService unit test:given id return movie")
    public void givenMovieId_getById_returnMovie() {
        given(movieRepository.findById(movie.getId()))
                .willReturn(Optional.of(movie));

        var movieR = service.getById(movie.getId());
        assertEquals(movie.getId(), movieR.getId());
    }

    @Test
    @DisplayName("MovieService unit test:given genre return movies")
    public void givenGenre_getMoviesByGenre_returnMovies() {
        given(movieRepository.findByGenre("mafia"))
                .willReturn(List.of(movie));

        var movieR = service.getMoviesByGenre("mafia").get(0);
        assertEquals(movie.getId(), movieR.getId());
        assertEquals(movie.getTitle(), movieR.getTitle());
    }

    @Test
    @DisplayName("MovieService unit test:given cinema name throw NotFoundException")
    public void givenCinemaName_getMoviesByCinema_throwNotFoundException() {
        given(cinemaRepository.findByName(ArgumentMatchers.anyString()))
                .willThrow(new NotFoundException("Cinema not found"));
        var exception = assertThrows(
            NotFoundException.class,
                () -> service.getMoviesByCinema("Capitol"),
                "Cinema not found"
        );
    }
}
