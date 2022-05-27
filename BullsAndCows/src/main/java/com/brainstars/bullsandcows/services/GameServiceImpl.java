package com.brainstars.bullsandcows.services;

import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @Override
    public void startGame(Principal principal) {
        int number = getRandomNumber();
        Game game = new Game();
        game.setNumberToGuess(number);
        game.setUsername(principal.getName());
        gameRepository.save(game);
      }
    private int getRandomNumber() {
        int num1 = getRandomDigit();
        int num2 = getRandomDigit();
        while (num1 == num2) {
            num2 = getRandomDigit();
        }

        int num3 = getRandomDigit();
        while (num3 == num1 || num3 == num2) {
            num3 = getRandomDigit();
        }

        int num4 = getRandomDigit();
        while (num4 == num1 || num4 == num2 || num4 == num3) {
            num4 = getRandomDigit();
        }

        return num1 * 1000 + num2 * 100 + num3 * 10 + num4;
    }
    private int getRandomDigit() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
