package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
