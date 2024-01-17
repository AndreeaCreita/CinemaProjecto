package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.exceptions.NotFoundException;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.repositories.ActorRepository;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    @Override
    public List<ActorDto> getActorsByMovie(Long movieId) {
        var movie = movieRepository.findById(movieId).orElseThrow(() ->
                new NotFoundException("Movie not found"));
        return actorRepository.findByMovie(movieId).stream().map(
            a -> new ActorDto(a.getId(), a.getName(),
                    a.getMovies().stream().map(Movie::getTitle).toList())
        ).toList();
    }
}
