package com.example.CinemaProjecto.controllers;

import com.example.CinemaProjecto.dtos.CinemaDto;
import com.example.CinemaProjecto.models.Cinema;
import com.example.CinemaProjecto.services.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<CinemaDto> getById(
        @PathVariable Long id
    ) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CinemaDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
