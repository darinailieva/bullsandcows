package com.brainstars.bullsandcows.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(name = "current_number")
  private String currentNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  @JsonIgnore
  Game game;
}
