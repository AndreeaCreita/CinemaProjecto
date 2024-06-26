package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.dtos.RequestUpdateTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
@Validated
public class TicketController {

    private final TicketService service;

    @Operation(description = "buy a ticket")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "The bought ticket",
                    responseCode = "201",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TicketDto.class)
                    )
            ),
            @ApiResponse(
                    description = "User/cinema/movie not found",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            ),
            @ApiResponse(
                    description = "No more seats available",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            )
    })
    @PostMapping("/buy")
    public ResponseEntity<TicketDto> buyTicket(
        @Valid @RequestBody RequestCreateTicketDto requestCreateTicketDto
    ) {
        return new ResponseEntity<>(service.createTicket(requestCreateTicketDto), HttpStatus.CREATED);
    }

    @Operation(description = "Cancel a ticket")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Ticket cancelled",
                    responseCode = "204"
            ),
            @ApiResponse(
                    description = "Ticket not found",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            ),
            @ApiResponse(
                    description = "Ticket cannot be cancelled",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            )
    })
    @DeleteMapping("cancel/{id}")
    public void cancelTicket(
        @Min(1) @PathVariable @Parameter(description = "Ticket ID") Long id
    ) {
        service.cancelTicket(id);
    }

    @Operation(description = "Update a ticket date")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Ticket updated",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TicketDto.class)
                    )
            ),
            @ApiResponse(
                    description = "Ticket not found",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<TicketDto> modifyTicketDate(
        @Valid @RequestBody RequestUpdateTicketDto requestUpdateTicketDto
    ) {
        return new ResponseEntity<>(service.updateTicketDate(requestUpdateTicketDto.getTicketId(), requestUpdateTicketDto.getMovieTime()), HttpStatus.OK);
    }


    @GetMapping("/getAll")
    @Operation(summary = "Get all tickets", description = "Retrieve a list of all tickets")
    @ApiResponse(responseCode = "200", description = "Successful retrieval",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketDto.class)))
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> tickets = service.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a ticket by ID", description = "Retrieve a ticket by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ticket found",
                    content = @Content(schema = @Schema(implementation = TicketDto.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content)
    })
    public ResponseEntity<TicketDto> getTicketById(@PathVariable @Min(1) Long id) {
        TicketDto ticket = service.getTicketById(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
