package com.brainstars.bullsandcows.services;

import com.brainstars.bullsandcows.exceptions.DuplicateDigitException;
import com.brainstars.bullsandcows.exceptions.EntityNotFoundException;
import com.brainstars.bullsandcows.exceptions.InvalidLengthException;
import com.brainstars.bullsandcows.exceptions.InvalidSymbolException;
import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;
import com.brainstars.bullsandcows.repositories.AttemptRepository;
import com.brainstars.bullsandcows.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public Game startGame(String username) {
        String numberToGuess = getRandomNumber();
        Game game = new Game();
        game.setNumberToGuess(numberToGuess);
        game.setUsername(username);
        game.setFinished(false);
        return gameRepository.save(game);
    }

    @Override
    public void guessNumber(int gameId, Attempt attempt) {
        String currentNumber = attempt.getCurrentNumber();
        validateCurrentNumberLength(attempt.getCurrentNumber());
        validateSymbols(currentNumber);
        validateNoDuplicates(currentNumber);

        Game game = getById(gameId);
        String numberToGuess = game.getNumberToGuess();
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
    public List<UsersResponse> findAllByMinTimesPlayedAndMinDateDifference() {
        return gameRepository.findAllByMinTimesPlayedAndMinDateDifference();
    }

    @Override
    public Game getById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game", "id", String.valueOf(gameId)));
    }

    @Override
    public List<Game> getAllUserGames(String username) {
        return gameRepository.getAllUserGames(username);
    }

    private String getRandomNumber() {
        Random random = new Random();
        int randomNumberOrigin = 0;
        int randomNumberBound = 10;
        int size = 4;
        int[] unique = random.ints(randomNumberOrigin, randomNumberBound).distinct().limit(size).toArray();
        return Arrays.toString(unique).replace("[", "").replace("]", "").replace(", ", "");
    }

    private boolean isCow(String numberToGuess, String currentNumber, int i, int j) {
        return numberToGuess.charAt(i) == currentNumber.charAt(j);
    }

    private boolean isBull(String numberToGuess, String currentNumber, int i, int j) {
        return i == j && isCow(numberToGuess, currentNumber, i, j);
    }

    private void validateCurrentNumberLength(String currentNumber) {
        int length = currentNumber.length();
        if (length != 4) {
            throw new InvalidLengthException();
        }
    }

    private void validateSymbols(String currentNumber) {
        if (!currentNumber.matches("^[0-9]*$")) {
            throw new InvalidSymbolException();
        }
    }

    private void validateNoDuplicates(String currentNumber) {
        int length = currentNumber.length();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j && currentNumber.charAt(i) == currentNumber.charAt(j)) {
                    throw new DuplicateDigitException();
                }
            }
        }
    }
}
