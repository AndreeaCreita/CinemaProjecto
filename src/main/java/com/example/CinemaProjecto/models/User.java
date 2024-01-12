package com.example.CinemaProjecto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    //entitatea mea <3
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //cand adaugi un nou user in baza de date, sa iti adauge un nou id singur
    private Long id;
    private String lastName;
    private String firstName;

}
