package com.example.CinemaProjecto.controller;

import com.example.CinemaProjecto.advice.ExceptionAdvice;
import com.example.CinemaProjecto.controllers.ActorController;
import com.example.CinemaProjecto.controllers.CinemaController;
import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Actor;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.services.implementations.CinemaServiceImpl;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CinemaController.class)
@WebMvcTest(CinemaController.class)
@Import({ExceptionAdvice.class})
@ActiveProfiles("h2")

public class CinemaControllerTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @MockBean
    private CinemaServiceImpl service;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @DisplayName("CinemaService: given nonexistent cinema id return 404")
    public void givenCinemaId_getById_return404() throws Exception {
        when(service.getById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("Cinema not found"));

        mvc.perform(get("/cinemas/get/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cinema not found"));
    }

    @Test
    @DisplayName("CinemaService unit test: return all cinemas")
    public void getAll_returnListOfCinemas() throws Exception {
        when(service.getAll()).thenReturn(
                List.of(new CinemaDto(1L, "Cinema City", "Afi"))
        );

        mvc.perform(get("/cinemas/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Cinema City"));
    }

}
