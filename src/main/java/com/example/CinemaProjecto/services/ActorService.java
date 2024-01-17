package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getActorsByMovie(Long movieId);
}
