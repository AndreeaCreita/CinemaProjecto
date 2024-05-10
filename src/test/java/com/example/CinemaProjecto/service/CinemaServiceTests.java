package com.example.CinemaProjecto.service;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Adresa;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.services.implementations.CinemaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CinemaServiceTests {
    @InjectMocks
    private CinemaServiceImpl service;

    @Mock
    private CinemaRepository repository;



    @Test
    @DisplayName("CinemaService: given nonexistent cinema id throw NotFoundException")
    public void givenCinemaId_getById_throwNotFoundException() {
        given(repository.findById(ArgumentMatchers.anyLong()))
                .willThrow(new NotFoundException("Cinema not found"));

        var exception = assertThrows(NotFoundException.class, () ->
                service.getById(100L));
        assertEquals("Cinema not found", exception.getMessage());
    }

    @Test
    @DisplayName("CinemaService unit test: return all cinemas")
    public void getAll_returnListOfCinemas() {
        given(repository.findAll()).willReturn(
                List.of(new Cinema(1L, "Cinema City", "Afi", List.of(), List.of(), new Adresa()))
        );

        var cinemas = service.getAll();
        assertEquals("Cinema City", cinemas.get(0).getName());
        assertEquals("Afi", cinemas.get(0).getLocation());
    }
}
