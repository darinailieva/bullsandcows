package com.brainstars.bullsandcows.services;

import com.brainstars.bullsandcows.models.Game;

import java.security.Principal;

public interface GameService {
    void startGame(Principal principal);
}
