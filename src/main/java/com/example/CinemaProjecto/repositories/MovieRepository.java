package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String name);
    @Query("SELECT m FROM Movie m JOIN m.genres g where g.name = :genre")
    List<Movie> findByGenre(String genre);


    @Query("SELECT m FROM Movie m JOIN m.cinemaMovies cm where cm.cinema.name = :cinema")
    List<Movie> findByCinema(String cinema);
}
