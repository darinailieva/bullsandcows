package com.brainstars.bullsandcows.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersResponse {
    private String username;
    private int numberOfFinishedGames;
    private int bestTimesPlayed;
    private int bestTime;
}
