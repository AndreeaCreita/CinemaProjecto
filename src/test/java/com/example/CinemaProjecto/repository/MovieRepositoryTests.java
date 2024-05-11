package com.example.CinemaProjecto.repository;

import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.models.Genre;
import com.example.CinemaProjecto.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;
    private Genre genre;

    @BeforeEach
    public void init() {
        // Create and persist the genre
        Genre genre = new Genre();
        genre.setName("Drama");
        entityManager.persist(genre);

        // Create and persist the movie
        movie = Movie.builder()
                .title("The Shawshank Redemption")
                .description("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .genres(List.of(genre)) // associate the genre with the movie
                .actors(new ArrayList<>())
                .tickets(new ArrayList<>())
                .cinemaMovies(new ArrayList<>())
                .build();
        entityManager.persist(movie);

        // Flush changes to ensure they are applied to the database
        entityManager.flush();
    }
    @Test
    public void whenFindByTitleContainingIgnoreCase_thenReturnsMovies() {
        List<Movie> results = movieRepository.findByTitleContainingIgnoreCase("shawshank");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    public void whenFindByGenre_thenReturnsMovies() {
        List<Movie> results = movieRepository.findByGenre("Drama");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getTitle()).isEqualTo(movie.getTitle());
    }
}
