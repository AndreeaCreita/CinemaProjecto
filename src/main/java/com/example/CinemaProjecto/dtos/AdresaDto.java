package com.example.CinemaProjecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdresaDto {
    private Long id;
    private String numar;
    private String oras;
    private String strada;
    private String tara;
    private Long codpostal;
}
