package com.example.CinemaProjecto.services.implementations;

import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.repositories.MovieRepository;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;
    @Override
    public List<MovieDto> getAll() {
        return null;
    }

    @Override
    public MovieDto getById(Long id) {
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByName(String name) {
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        return repository.findByGenre(genre).stream().map(m ->
                new MovieDto(
                        m.getTitle(),
                        m.getDescription(),
                        m.getGenres().stream().map(g -> g.getName()).toList(),
                        m.getCinema().getName()
                )).toList();
    }
}
