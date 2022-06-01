package com.brainstars.bullsandcows.mappers;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameMapper {
    public static Attempt convertToAttempt(AttemptRequest request) {
        Attempt attempt = new Attempt();
        if (Objects.nonNull(request)) {
            attempt.setCurrentNumber(request.getCurrentNumber());
        }
        return attempt;
    }

    public static List<UsersResponse> convertToUsersResponse(List<Object[]> users) {
        List<UsersResponse> usersResponses = new ArrayList<>();
        for (Object[] user : users) {
            var usersResponse = new UsersResponse();
            usersResponse.setUsername(String.valueOf(user[0]));
            usersResponse.setNumberOfFinishedGames(Integer.parseInt(user[1].toString()));
            usersResponse.setBestTimesPlayed(Integer.parseInt(user[2].toString()));
            usersResponse.setBestTime(Integer.parseInt(user[3].toString()));
            usersResponses.add(usersResponse);
        }
        return usersResponses;
    }
}
