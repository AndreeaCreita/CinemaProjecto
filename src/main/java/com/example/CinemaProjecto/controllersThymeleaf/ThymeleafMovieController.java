package com.example.CinemaProjecto.controllersThymeleaf;

import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThymeleafMovieController {

    private final MovieService movieService;

    @GetMapping("/movies/view/{id}")
    public String getMoviePage(@PathVariable Long id, Model model) {
        try {
            MovieDto movie = movieService.getById(id);
            model.addAttribute("movie", movie);
            return "movie";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while trying to fetch movie details.");
            return "error";
        }
    }

    @GetMapping("/movies/genre/view/{genre}")
    public String getMoviesByGenre(@PathVariable String genre, Model model) {
        List<MovieDto> movies = movieService.getMoviesByGenre(genre);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Movies by Genre: " + genre);
        return "movie-list";
    }

    @GetMapping("/movies/cinema/view/{cinemaName}")
    public String getMoviesByCinema(@PathVariable String cinemaName, Model model) {
        List<MovieDto> movies = movieService.getMoviesByCinema(cinemaName);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Movies in Cinema: " + cinemaName);
        return "movie-list";
    }

    @GetMapping("/movies/view/all")
    public String getAllMovies(Model model, @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        Page<MovieDto> movies = movieService.getAll(pageable);
        model.addAttribute("page", movies);
        model.addAttribute("title", "All Movies");
        return "movies-page";
    }
}