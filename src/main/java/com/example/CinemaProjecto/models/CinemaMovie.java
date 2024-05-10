package com.example.CinemaProjecto.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class CinemaMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seats;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
