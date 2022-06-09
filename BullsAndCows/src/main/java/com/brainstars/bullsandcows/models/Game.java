package com.brainstars.bullsandcows.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column (name = "number_to_guess")
    private String numberToGuess;

    @Column (name = "username")
    private String username;

    @CreationTimestamp
   // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    @UpdateTimestamp
  //  @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
   // @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime lastModifiedDate;

    @Column (name = "finished")
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
