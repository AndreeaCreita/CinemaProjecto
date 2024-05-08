package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.exceptions.NoSeatsException;
import com.example.CinemaProjecto.exceptions.TicketNotCancellable;
import com.example.CinemaProjecto.models.Ticket;
import com.example.CinemaProjecto.repositories.CinemaMovieRepository;
import com.example.CinemaProjecto.repositories.TicketRepository;
import com.example.CinemaProjecto.repositories.UserRepository;
import com.example.CinemaProjecto.services.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CinemaMovieRepository cinemaMovieRepository;
    private final UserRepository userRepository;


    @Override
    public TicketDto createTicket(RequestCreateTicketDto requestCreateTicketDto) {
        var cinemaMovie = cinemaMovieRepository.findByCinemaIdAndMovieId(
            requestCreateTicketDto.getCinemaId(), requestCreateTicketDto.getMovieId()
        );
        if (cinemaMovie == null) {
            throw new NotFoundException("Cinema/movie combination not found");
        }
        var user = userRepository.findById(requestCreateTicketDto.getUserId()).orElseThrow(() ->
                new NotFoundException("User not found"));
        var takenSeats = ticketRepository.getTakenSeats(requestCreateTicketDto.getCinemaId(), requestCreateTicketDto.getMovieId());
        if (Objects.equals(takenSeats, cinemaMovie.getSeats())) {
            throw new NoSeatsException("All seats are taken. Please choose another movie/cinema");
        }
        var ticket = ticketRepository.save(
                new Ticket(null, user, cinemaMovie.getMovie(), cinemaMovie.getCinema(), requestCreateTicketDto.getMovieTime())
        );
        log.info("Ticket added in db, ticket id: " + ticket.getId());
        return new TicketDto(
                ticket.getId(),
                user.getEmail(),
                cinemaMovie.getMovie().getTitle(),
                cinemaMovie.getCinema().getName(),
                ticket.getDateTime()
        );
    }

    @Override
    public void cancelTicket(Long ticketId) {
        var ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new NotFoundException("Ticket not found"));
        if (ticket.getDateTime().isBefore(LocalDateTime.now())) {
            throw new TicketNotCancellable("Ticket cannot be cancelled");
        }
        log.info("Ticket with id " + ticket.getId() + " deleted from db");
        ticketRepository.delete(ticket);

    }

    @Override
    public TicketDto updateTicketDate(Long ticketId, LocalDateTime time) {
        var ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new NotFoundException("Ticket not found"));
        ticket.setDateTime(time);
        var modifiedTicket = ticketRepository.save(ticket);
        log.info("Ticket updated in db, ticket id: " + ticket.getId());
        return new TicketDto(
            modifiedTicket.getId(),
            modifiedTicket.getUser().getEmail(),
            modifiedTicket.getMovie().getTitle(),
            modifiedTicket.getCinema().getName(),
            modifiedTicket.getDateTime()
        );
    }
}
