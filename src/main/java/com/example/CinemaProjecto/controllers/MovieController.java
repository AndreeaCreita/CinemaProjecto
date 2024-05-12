package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.Movie;
import com.example.CinemaProjecto.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class  MovieController {
    private final MovieService service;

    @Operation(description = "get movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "The movie",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MovieDto.class)
                    )
            ),
            @ApiResponse(
                description = "Movie not found",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            )
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<MovieDto> getById(
            @PathVariable @Parameter(description = "Movie id") Long id
    ){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @Operation(description = "get movies by genre")
    @ApiResponses(value = {
        @ApiResponse(
                description = "Movies filtered by genre",
                responseCode = "200",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))
                )
        )
    })
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDto>> getByGenre(
        @PathVariable @NotBlank @Parameter(description = "Genre to filter by") String genre
    ) {
        return new ResponseEntity<>(service.getMoviesByGenre(genre), HttpStatus.OK);
    }

    @Operation(description = "get movies by cinema")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "The list of movies",
                    responseCode = "200",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))
                    )
            ),
            @ApiResponse(
                    description = "Cinema not found",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            )
    })
    @GetMapping("/cinema/{cinema}")
    public ResponseEntity<List<MovieDto>> getCinema(
        @PathVariable @Parameter(description = "The name of the cinema to search the movies for") String cinema
            ) {
        return new ResponseEntity<>(service.getMoviesByCinema(cinema), HttpStatus.OK);
    }

    @Operation(description = "Get all movies with pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Paginated list of all movies",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Page.class)
                    )
            )
    })

    @GetMapping("/all")
    public ResponseEntity<Page<MovieDto>> getAllMovies(
            @PageableDefault(size = 5, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<MovieDto> movies = service.getAll(pageable);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}
