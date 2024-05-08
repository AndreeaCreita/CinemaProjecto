package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdresaDto {
    private Long id;
    private String numar; // Number
    private String oras; // City
    private String strada; // Street
    private String tara; // Country
    private Long codpostal; // Postal Code
}
