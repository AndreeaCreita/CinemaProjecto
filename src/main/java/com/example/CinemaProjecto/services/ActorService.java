package com.example.CinemaProjecto.services;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.dtos.UserDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getActorsByMovie(Long movieId);

    ActorDto getActorById(Long actorId);
}
