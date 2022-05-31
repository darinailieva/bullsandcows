package com.brainstars.bullsandcows.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "attempts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private int attemptId;

    @Column(name = "bulls")
    private int bulls;

    @Column(name = "cows")
    private int cows;

    @Column (name = "current_number")
    private String currentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @JsonIgnore
    Game game;
}
