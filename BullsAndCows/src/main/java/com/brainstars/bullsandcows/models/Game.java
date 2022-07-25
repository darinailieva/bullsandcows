package com.brainstars.bullsandcows.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Game implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "game_id")
  private Integer gameId;

  @Column(name = "times_played")
  private Integer timesPlayed;

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
  private Boolean finished;

  @OneToMany(mappedBy = "game",
    cascade = CascadeType.ALL,
    orphanRemoval = true) @ToString.Exclude
  private List<Attempt> attempts = new ArrayList<>();

  public void addAttempt(Attempt attempt) {
    attempts.add(attempt);
    attempt.setGame(this);
  }
}
