package com.example.CinemaProjecto.repositories;

import com.example.CinemaProjecto.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT cm.seats - count(t) FROM CinemaMovie cm, Ticket t WHERE cm.cinema.id = :cinemaId AND cm.movie.id = :movieId AND t.cinema.id = :cinemaId AND t.movie.id = :movieId")
    Integer getSeats(Long cinemaId, Long movieId);
}
