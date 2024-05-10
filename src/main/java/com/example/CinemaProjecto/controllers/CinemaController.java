package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.dtos.MovieDto;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.services.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/cinemas")
@RequiredArgsConstructor
public class CinemaController {
    public final CinemaService service;

    @Operation(description = "get cinema by id")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "The cinema",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CinemaDto.class)
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
    @GetMapping("/get/{id}")
    public ResponseEntity<CinemaDto> getById(
        @PathVariable @Min(1) @Parameter(description = "Cinema Id") Long id
    ) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @Operation(description = "get all cinemas")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "The cinemas",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CinemaDto.class))
                    )
            )
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<CinemaDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
