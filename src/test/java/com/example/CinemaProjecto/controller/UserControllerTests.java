package com.example.CinemaProjecto.controller;


import com.example.CinemaProjecto.advice.ExceptionAdvice;
import com.example.CinemaProjecto.controllers.TicketController;
import com.example.CinemaProjecto.controllers.UserController;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.models.CinemaMovie;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.services.implementations.TicketServiceImpl;
import com.example.CinemaProjecto.services.implementations.UserServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserController.class)
@WebMvcTest(UserController.class)
@Import({ExceptionAdvice.class})
public class UserControllerTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @MockBean
    private UserServiceImpl service;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @DisplayName("UserController unit test:given user id return 404")
    public void givenUserId_getUser_return404() throws Exception {
        when(service.getUser(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("User not found"));

        mvc.perform(get("/users/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
