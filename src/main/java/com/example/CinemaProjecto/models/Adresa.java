package com.example.CinemaProjecto.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adresa")
public class Adresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numar;

    @Column(nullable = false)
    private String oras;

    @Column(nullable = false)
    private String strada;

    @Column(nullable = false)
    private String tara;

    @Column(nullable = false)
    private String codpostal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    @JsonIgnore
    private Cinema cinema;
}
