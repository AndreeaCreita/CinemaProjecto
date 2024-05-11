package com.example.CinemaProjecto.controller;

import com.example.CinemaProjecto.advice.ExceptionAdvice;
import com.example.CinemaProjecto.controllers.ActorController;
import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Actor;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.service.ActorServiceTests;
import com.example.CinemaProjecto.services.ActorService;
import com.example.CinemaProjecto.services.implementations.ActorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ActorController.class)
@WebMvcTest(ActorController.class)
@Import({ExceptionAdvice.class})
@ActiveProfiles("h2")

public class ActorControllerTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @MockBean
    private ActorServiceImpl service;

    private Movie movie;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        movie = new Movie(
                1L, "The Dark Knight", "Batman fights Joker",
                null, null, null, null
        );
        movie.setActors(
                List.of(
                        new Actor(1L, "Christian Bale", List.of(movie)),
                        new Actor(2L, "Heath Ledger", List.of(movie))
                )
        );
    }

    @Test
    @DisplayName("ActorController unit test: given movieId return the list of actors in it")
    public void givenMovieId_getActorsByMovie_returnListOfActors() throws Exception {
        when(service.getActorsByMovie(movie.getId()))
                .thenReturn(movie.getActors().stream().map(a -> new ActorDto(
                        a.getId(), a.getName(), a.getMovies().stream().map(Movie::getTitle).toList()
                )).toList());

        mvc.perform(get("/actor/movie/{id}", movie.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Heath Ledger"));
    }

    @Test
    @DisplayName("ActorController unit test: given nonexistent movieId return 404")
    public void givenMovieId_getActorsByMovie_return404() throws Exception {
        when(service.getActorsByMovie(movie.getId()))
                .thenThrow(new NotFoundException("Movie not found"));

        mvc.perform(get("/actor/movie/{id}", movie.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Movie not found"));
    }
}
