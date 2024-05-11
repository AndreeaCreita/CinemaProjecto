package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.ActorDto;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable Long id) {
        return new ResponseEntity<>(actorService.getActorById(id), HttpStatus.OK);
    }

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
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ActorDto>> getActorsByMovie(
        @PathVariable @Min(1) @Parameter(description = "Movie ID") Long id
    ) {
        return new ResponseEntity<>(actorService.getActorsByMovie(id), HttpStatus.OK);
    }
}
