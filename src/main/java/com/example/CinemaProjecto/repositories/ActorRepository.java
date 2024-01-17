package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT a FROM Actor a JOIN a.movies m WHERE m.id = :movieId")
    List<Actor> findByMovie(Long movieId);
}
