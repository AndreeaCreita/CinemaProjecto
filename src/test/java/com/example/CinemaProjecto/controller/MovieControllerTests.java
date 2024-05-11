package com.example.CinemaProjecto.controller;

import com.example.CinemaProjecto.advice.ExceptionAdvice;
import com.example.CinemaProjecto.controllers.CinemaController;
import com.example.CinemaProjecto.controllers.MovieController;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.services.implementations.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MovieController.class)
@WebMvcTest(MovieController.class)
@Import({ExceptionAdvice.class})
@ActiveProfiles("h2")

public class MovieControllerTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @MockBean
    private MovieServiceImpl service;

    private Movie movie;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        movie = new Movie(
                1L, "The Godfather", "Vito Corleone is the head of the Corleone mafia family",
                null, new ArrayList<Actor>(),
                new ArrayList<Ticket>(), new ArrayList<CinemaMovie>()
        );
        movie.setGenres(List.of(new Genre(1L, "mafia", List.of(movie))));
    }

    @Test
    @DisplayName("MovieController unit test:given id return movie")
    public void givenMovieId_getById_returnListOfMovies() throws Exception {
        when(service.getById(ArgumentMatchers.anyLong())).thenReturn(
            new MovieDto(
              movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenres().stream().map(Genre::getName).toList(),
                    movie.getActors().stream().map(Actor::getName).toList(), movie.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
            )
        );
        mvc.perform(get("/movies/get/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect(jsonPath("$.title").value(movie.getTitle()));
    }

    @Test
    @DisplayName("MovieController unit test:given genre return movies")
    public void givenGenre_getByGenre_returnMovies() throws Exception {
        when(service.getMoviesByGenre("mafia"))
                .thenReturn(List.of(new MovieDto(
                        movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenres().stream().map(Genre::getName).toList(),
                        movie.getActors().stream().map(Actor::getName).toList(), movie.getCinemaMovies().stream().map(cm -> cm.getCinema().getName()).toList()
                )));

        mvc.perform(get("/movies/genre/{genre}", "mafia"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(movie.getId()))
                .andExpect(jsonPath("$[0].title").value(movie.getTitle()));
    }

    @Test
    @DisplayName("MovieController unit test:given cinema name return 404")
    public void givenCinemaName_getCinema_return404() throws Exception {
        given(service.getMoviesByCinema(ArgumentMatchers.anyString()))
                .willThrow(new NotFoundException("Cinema not found"));

        mvc.perform(get("/movies/cinema/{cinema}", "Afi"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cinema not found"));
    }
}
