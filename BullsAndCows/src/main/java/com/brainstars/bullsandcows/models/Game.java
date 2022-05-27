package com.brainstars.bullsandcows.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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
    private int numberToGuess;

    @Column (name = "username")
    private String username;

    @CreatedDate
    @Column(name = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastModifiedDate;

    @Column (name = "finished")
    boolean isFinished;
}
