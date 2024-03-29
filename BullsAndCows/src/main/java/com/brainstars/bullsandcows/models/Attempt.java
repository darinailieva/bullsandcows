package com.brainstars.bullsandcows.models;

import java.io.Serializable;

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
import org.springframework.cache.annotation.Cacheable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "attempts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@ToString

public class Attempt implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "attempt_id")
  private Integer attemptId;

  @Column(name = "bulls")
  private Integer bulls;

  @Column(name = "cows")
  private Integer cows;

  @Column(name = "current_number")
  private String currentNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id")
  @JsonIgnore @ToString.Exclude
  private Game game;
}
