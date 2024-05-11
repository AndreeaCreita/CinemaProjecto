package com.example.CinemaProjecto.repository;

import com.example.CinemaProjecto.models.Actor;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ActorRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActorRepository actorRepository;

    private Movie movie;
    private Actor actor;

    @BeforeEach
    public void init() {
        movie = new Movie();
        movie.setTitle("Example Movie");

        actor = Actor.builder()
                .name("Example Actor")
                .movies(Arrays.asList(movie))
                .build();
        movie.setActors(List.of(actor));
        entityManager.persist(movie);

        entityManager.persist(actor);

        entityManager.flush();
    }

    @Test
    public void whenFindByMovie_thenReturnActors() {
        Long movieId = movie.getId();
        var actors = actorRepository.findByMovie(movieId);
        assertThat(actors).isNotEmpty();
        assertThat(actors.get(0).getName()).isEqualTo(actor.getName());
    }
}
