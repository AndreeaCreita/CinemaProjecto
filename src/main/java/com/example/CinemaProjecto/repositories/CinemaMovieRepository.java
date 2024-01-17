package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.CinemaMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaMovieRepository extends JpaRepository<CinemaMovie, Long> {
    @Query("SELECT cm.cinema FROM CinemaMovie cm WHERE cm.movie.id = :movieId")
    List<Cinema> findByMovieId(Long movieId);
    CinemaMovie findByCinemaIdAndMovieId(Long cinemaId, Long movieId);
}
