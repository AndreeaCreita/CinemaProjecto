package com.example.CinemaProjecto.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "cinema")
    private List<Ticket> tickets;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "cinema_movie",
//            joinColumns = @JoinColumn(name = "cinema_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id")
//    )
//    private List<Movie> movies;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cinema")
    private List<CinemaMovie> cinemaMovies;

    @OneToOne(mappedBy = "cinema", cascade = CascadeType.ALL)
    private Adresa adresa;
}
