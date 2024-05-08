package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.services.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository repository;
    @Override
    public CinemaDto getById(Long id) {
        Cinema cinema = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Cinema not found"));
        log.info("Retrieved cinema, ID: " + cinema.getId() + ", Name: " + cinema.getName());
        return new CinemaDto(
                cinema.getId(),
                cinema.getName(),
                cinema.getLocation()
        );
    }

    @Override
    public List<CinemaDto> getAll() {
        return repository.findAll().stream().map(c -> {
            log.info("Processing cinema, ID: " + c.getId() + ", Name: " + c.getName());

            return new CinemaDto(
                    c.getId(),
                    c.getName(),
                    c.getLocation()
            );
        }).toList();
    }
}
