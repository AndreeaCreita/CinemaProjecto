package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.repositories.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CinemaService {
    CinemaDto getById(Long id);
    List<CinemaDto> getAll();
}
