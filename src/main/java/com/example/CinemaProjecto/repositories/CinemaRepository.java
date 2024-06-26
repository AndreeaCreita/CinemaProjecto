package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findByName(String cinemaName);
}
