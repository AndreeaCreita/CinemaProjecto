package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import com.example.CinemaProjecto.services.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository repository;
    @Override
    public CinemaDto getById(Long id) {
        Cinema cinema = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Cinema not found"));
        return new CinemaDto(
                cinema.getId(),
                cinema.getName(),
                cinema.getLocation()
        );
    }

    @Override
    public List<CinemaDto> getAll() {
        return repository.findAll().stream().map(c ->
                new CinemaDto(
                        c.getId(),
                        c.getName(),
                        c.getLocation()
                )).toList();
    }
}
