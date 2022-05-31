package com.brainstars.bullsandcows.mappers;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.dtos.AttemptRequest;

import java.util.Objects;

public class GameMapper {
    public static Attempt convertToAttempt(AttemptRequest request) {
        Attempt attempt = new Attempt();
        if (Objects.nonNull(request)) {
            attempt.setCurrentNumber(request.getCurrentNumber());
        }
        return attempt;
    }
}
