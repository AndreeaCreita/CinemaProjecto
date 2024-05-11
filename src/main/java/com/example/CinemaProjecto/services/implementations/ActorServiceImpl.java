package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.dtos.UserDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Actor;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.ActorRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.ActorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;


    @Override
    public ActorDto getActorById(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow(() ->
                new NotFoundException("Actor not found with id: " + actorId));

        return new ActorDto(actor.getId(), actor.getName(), actor.getMovies().stream().map(Movie::getTitle).toList());
    }
    @Override
    public List<ActorDto> getActorsByMovie(Long movieId) {
        var movie = movieRepository.findById(movieId).orElseThrow(() ->
                new NotFoundException("Movie not found"));

        log.info("Retrieved movie for actor processing, Movie ID: " + movie.getId() + ", Movie Title: " + movie.getTitle());

        return actorRepository.findByMovie(movieId).stream().map(a -> {
            log.info("Processing actor, Actor ID: " + a.getId() + ", Actor Name: " + a.getName());

            return new ActorDto(
                    a.getId(),
                    a.getName(),
                    a.getMovies().stream().map(Movie::getTitle).toList()
            );
        }).toList();
    }



}
