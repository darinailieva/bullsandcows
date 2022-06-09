package com.brainstars.bullsandcows.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "game_id")
  private int gameId;

  @Column(name = "times_played")
  private int timesPlayed;

  @Column(name = "number_to_guess")
  private String numberToGuess;

  @Column(name = "username")
  private String username;

  @CreationTimestamp
  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @UpdateTimestamp
  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;

  @Column(name = "finished")
  boolean isFinished;

  @OneToMany(mappedBy = "game",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
  List<Attempt> attempts;

  public void addAttempt(Attempt attempt) {
    attempts.add(attempt);
    attempt.setGame(this);
  }
}
