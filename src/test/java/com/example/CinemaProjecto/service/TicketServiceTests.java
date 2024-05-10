package com.example.CinemaProjecto.service;

import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.exceptions.NoSeatsException;
import com.example.CinemaProjecto.exceptions.TicketNotCancellable;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.repositories.CinemaMovieRepository;
import com.example.CinemaProjecto.repositories.TicketRepository;
import com.example.CinemaProjecto.repositories.UserRepository;
import com.example.CinemaProjecto.services.implementations.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {

    @InjectMocks
    private TicketServiceImpl service;

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private CinemaMovieRepository cinemaMovieRepository;
    @Mock
    private UserRepository userRepository;

    private CinemaMovie cinemaMovie;

    @BeforeEach
    public void before() {
        cinemaMovie = new CinemaMovie(
                1L,
                60,
                null,
                null
        );
        cinemaMovie.setCinema(new Cinema(1L, "Cinema", "Afi", List.of(), List.of(), new Adresa()));
        cinemaMovie.setMovie(new Movie(1L, "Shrek", "Ogre", List.of(), List.of(), List.of(), List.of(cinemaMovie)));
    }

    @Test
    @DisplayName("TicketService unit test:given new ticket throw NoSeatsException")
    public void givenNewTicket_createTicket_throwNoSeatsException() {
        given(cinemaMovieRepository.findByCinemaIdAndMovieId(1L, 1L))
                .willReturn(cinemaMovie);
        given(ticketRepository.getTakenSeats(1L, 1L))
                .willReturn(60);
        given(userRepository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(new User()));

        assertThrows(NoSeatsException.class,
                () -> service.createTicket(new RequestCreateTicketDto(1L, 1L, 1L, LocalDateTime.MAX)),
                "All seats are taken. Please choose another movie/cinema");
    }

    @Test
    @DisplayName("TicketService unit test:given ticket id cancel and throw TicketNotCancellable")
    public void givenTicketId_cancelTicket_throwTicketNotCancellable() {
        given(ticketRepository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(new Ticket(1L, null, null, null, LocalDateTime.MIN)));

        assertThrows(TicketNotCancellable.class,
                () -> service.cancelTicket(1L),
                "Ticket cannot be cancelled");
    }
}
