package com.example.CinemaProjecto.controller;

import com.example.CinemaProjecto.advice.ExceptionAdvice;
import com.example.CinemaProjecto.controllers.CinemaController;
import com.example.CinemaProjecto.controllers.TicketController;
import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.exceptions.NoSeatsException;
import com.example.CinemaProjecto.models.*;
import com.example.CinemaProjecto.services.implementations.TicketServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.*;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TicketController.class)
@WebMvcTest(TicketController.class)
@Import({ExceptionAdvice.class})
public class TicketControllerTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @MockBean
    private TicketServiceImpl service;

    private CinemaMovie cinemaMovie;

    private Adresa adresa;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        cinemaMovie = new CinemaMovie(
                1L,
                60,
                null,
                null
        );
        adresa = Adresa.builder()
                .id(1L)
                .build();
        cinemaMovie.setCinema(new Cinema(1L, "Cinema", "Afi", List.of(), List.of(), adresa));
        cinemaMovie.setMovie(new Movie(1L, "Shrek", "Ogre", List.of(), List.of(), List.of(), List.of(cinemaMovie)));
    }

    @Test
    @DisplayName("TicketController unit test:given new ticket return 409")
    public void givenNewTicket_createTicket_return409() throws Exception {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // fara el nu merge
        var ticketDto = new RequestCreateTicketDto(1L, 1L, 1L, LocalDateTime.MAX);
        var stringDto = mapper.writeValueAsString(ticketDto);
        when(service.createTicket(ArgumentMatchers.any()))
                .thenThrow(new NoSeatsException("All seats are taken. Please choose another movie/cinema"));

        mvc.perform(post("/tickets/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringDto))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("All seats are taken. Please choose another movie/cinema"));
    }
}
