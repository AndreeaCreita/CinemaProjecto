package com.example.CinemaProjecto.repository;

import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CinemaRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CinemaRepository cinemaRepository;

    private Cinema cinema;

    @BeforeEach
    public void init() {
        cinema = Cinema.builder()
                .name("Regal Cinemas")
                .location("Downtown LA")
                .build();
        entityManager.persist(cinema);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnsCinema() {
        Cinema found = cinemaRepository.findById(cinema.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(cinema.getName());
        assertThat(found.getLocation()).isEqualTo(cinema.getLocation());
    }

    @Test
    public void whenFindAll_thenReturnsAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();

        assertThat(cinemas).isNotEmpty();
        assertThat(cinemas).contains(cinema);
    }
}
