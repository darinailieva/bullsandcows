package com.brainstars.bullsandcows.services;

import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.repositories.AttemptRepository;
import com.brainstars.bullsandcows.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private AttemptRepository attemptRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, AttemptRepository attemptRepository) {
        this.gameRepository = gameRepository;
        this.attemptRepository = attemptRepository;
    }

    @Override
    public Game startGame(Principal principal) {
        int number = getRandomNumber();
        String strNumber = String.format("%04d", number);
        Game game = new Game();
        game.setNumberToGuess(strNumber);
        game.setUsername(principal.getName());
        return gameRepository.save(game);
    }

    @Override
    public void guessNumber(int gameId, Attempt attempt) {
        Game game = getById(gameId);

        String numberToGuess = game.getNumberToGuess();
        String currentNumber = attempt.getCurrentNumber();
        int bulls = 0;
        int cows = 0;
        if (Objects.equals(numberToGuess, currentNumber)) {
            bulls = 4;
            game.setFinished(true);
        } else {
            for (int i = 0; i < numberToGuess.length(); i++) {
                for (int j = 0; j < currentNumber.length(); j++) {
                    if (isBull(numberToGuess, currentNumber, i, j)) {
                        bulls++;
                        break;
                    }
                    if (isCow(numberToGuess, currentNumber, i, j)) {
                        cows++;
                        break;
                    }
                }
            }
        }
        attempt.setBulls(bulls);
        attempt.setCows(cows);
        attemptRepository.save(attempt);
        game.addAttempt(attempt);
        game.setTimesPlayed(game.getAttempts().size());
        gameRepository.save(game);
    }

    @Override
    public List<Object[]> findAllByMinTimesPlayedAndMinDateDifference() {
        return gameRepository.findAllByMinTimesPlayedAndMinDateDifference();
    }
    @Override
    public Game getById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Game> getAllUserGames(String username) {
        return gameRepository.getAllUserGames(username);
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

    private boolean isCow(String numberToGuess, String currentNumber, int i, int j) {
        return numberToGuess.charAt(i) == currentNumber.charAt(j);
    }

    private boolean isBull(String numberToGuess, String currentNumber, int i, int j) {
        return i == j && isCow(numberToGuess, currentNumber, i, j);
    }


}
