package com.example.CinemaProjecto.repository;

import com.example.CinemaProjecto.models.Ticket;
import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TicketRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    private Ticket ticket;
    private User user;
    private Cinema cinema;
    private Movie movie;

    @BeforeEach
    public void init() {

        user = User.builder()
                .email("user@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();
        entityManager.persist(user);

        cinema = new Cinema();
        cinema.setName("AMC Theaters");
        cinema.setLocation("1234 Main St");
        entityManager.persist(cinema);

        movie = new Movie();
        movie.setTitle("Inception");

        entityManager.persist(movie);

        ticket = Ticket.builder()
                .user(user)
                .movie(movie)
                .cinema(cinema)
                .dateTime(LocalDateTime.now().plusDays(1))
                .build();
        entityManager.persist(ticket);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnsTicket() {
        Ticket found = ticketRepository.findById(ticket.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getUser()).isEqualToComparingFieldByField(ticket.getUser());
        assertThat(found.getMovie()).isEqualToComparingFieldByField(ticket.getMovie());
        assertThat(found.getCinema()).isEqualToComparingFieldByField(ticket.getCinema());
        assertThat(found.getDateTime()).isEqualTo(ticket.getDateTime());
    }

    @Test
    public void whenDeleteById_thenRemovedFromDatabase() {

        ticketRepository.deleteById(ticket.getId());
        ticketRepository.flush();

        Ticket found = ticketRepository.findById(ticket.getId()).orElse(null);
        assertThat(found).isNull();
    }
}
