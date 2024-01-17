package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT count(t) FROM Ticket t WHERE t.cinema.id = :cinemaId AND t.movie.id = :movieId")
    Integer getTakenSeats(Long cinemaId, Long movieId);
}
