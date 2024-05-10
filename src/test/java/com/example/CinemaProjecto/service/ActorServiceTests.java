package com.example.CinemaProjecto.service;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Actor;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.repositories.ActorRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.implementations.ActorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ActorServiceTests {

    @InjectMocks
    private ActorServiceImpl service;
    @Mock
    private ActorRepository actorRepository;
    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    public void before() {
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
    @DisplayName("ActorService unit test: given movieId return the list of actors in it")
    public void givenMovieId_getActorsByMovie_returnListOfActors() {
        given(movieRepository.findById(movie.getId()))
                .willReturn(Optional.ofNullable(movie));
        given(actorRepository.findByMovie(movie.getId()))
                .willReturn(movie.getActors());

        var actors = service.getActorsByMovie(movie.getId());
        assertEquals(movie.getActors().get(0).getName(), actors.get(0).getName());
        assertEquals(movie.getActors().get(1).getName(), actors.get(1).getName());
    }

    @Test
    @DisplayName("ActorService unit test: given nonexistent movieId throw NotFoundException")
    public void givenMovieId_getActorsByMovie_throwNotFoundException() {
        given(movieRepository.findById(movie.getId()))
                .willThrow(new NotFoundException("Movie not found"));

        var exception = assertThrows(NotFoundException.class, () ->
                service.getActorsByMovie(movie.getId()));
        assertEquals("Movie not found", exception.getMessage());
    }
}
