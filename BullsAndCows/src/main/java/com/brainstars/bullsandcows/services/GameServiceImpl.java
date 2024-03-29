package com.brainstars.bullsandcows.services;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.brainstars.bullsandcows.exceptions.DuplicateDigitException;
import com.brainstars.bullsandcows.exceptions.EntityNotFoundException;
import com.brainstars.bullsandcows.exceptions.InvalidLengthException;
import com.brainstars.bullsandcows.exceptions.InvalidSymbolException;
import com.brainstars.bullsandcows.models.Attempt;
import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.BullsCowsCounter;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;
import com.brainstars.bullsandcows.repositories.AttemptRepository;
import com.brainstars.bullsandcows.repositories.GameRepository;

import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
  private static final int MAX_DIGITS = 4;
  private static final int MAX_BOUND = 10;
  private static final int ZERO_INDEX = 0;
  private final GameRepository gameRepository;
  private final AttemptRepository attemptRepository;
  private final RBucket<Game> rBucket;

  @Autowired
  public GameServiceImpl(GameRepository gameRepository, AttemptRepository attemptRepository,
    RBucket<Game> rBucket) {
    this.gameRepository = gameRepository;
    this.attemptRepository = attemptRepository;
    this.rBucket = rBucket;
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
  public void guessNumber(Integer gameId, Attempt attempt) {
    String currentNumber = attempt.getCurrentNumber();
    validateCurrentNumberLength(attempt.getCurrentNumber());
    validateSymbols(currentNumber);
    validateNoDuplicates(currentNumber);

    Game game = getById(gameId);
    String numberToGuess = game.getNumberToGuess();

    BullsCowsCounter bullsCows = getBullsAndCows(numberToGuess, currentNumber);
    int bulls = bullsCows.getBulls();
    int cows = bullsCows.getCows();

    if (bulls == MAX_DIGITS) {
      game.setFinished(true);
    }

    attempt.setBulls(bulls);
    attempt.setCows(cows);
    game.addAttempt(attempt);
    attemptRepository.save(attempt);
    game.setTimesPlayed(game.getAttempts().size());
    rBucket.set(game);
    gameRepository.save(game);
  }

  @Override
  public List<UsersResponse> findAllByMinTimesPlayedAndMinDateDifference() {
    return gameRepository.findAllByMinTimesPlayedAndMinDateDifference();
  }

  @Override
  public Game getById(Integer gameId) {

    if (rBucket.isExists() && Objects.equals(rBucket.get().getGameId(), gameId)) {
      log.info("From bucket cache: " + rBucket.get().toString());
      return rBucket.get();
    }

    Game game = gameRepository.findById(gameId)
      .orElseThrow(() ->
        new EntityNotFoundException("Game", "id", String.valueOf(gameId)));
    if (game != null) {
      game.getAttempts().size();
    }
    log.info("From repo: " + game.toString());
    rBucket.set(game);

    return game;
  }

  @Override
  public List<Game> getAllUserGames(String username) {
    return gameRepository.getAllUserGames(username);
  }

  private String getRandomNumber() {
    Random random = new Random();
    int[] unique =
      random
        .ints(ZERO_INDEX, MAX_BOUND)
        .distinct()
        .limit(MAX_DIGITS).toArray();
    return Arrays.toString(unique)
      .replace("[", "")
      .replace("]", "")
      .replace(", ", "");
  }

  private BullsCowsCounter getBullsAndCows(String numberToGuess, String currentNumber) {
    BullsCowsCounter bullsCows = new BullsCowsCounter();
    for (int i = ZERO_INDEX; i < numberToGuess.length(); i++) {
      if (numberToGuess.charAt(i) == currentNumber.charAt(i)) {
        bullsCows.addBulls();
      } else if (numberToGuess.contains(String.valueOf(currentNumber.charAt(i)))) {
        bullsCows.addCows();
      }
    }
    return bullsCows;
  }

  private void validateCurrentNumberLength(String currentNumber) {
    int length = currentNumber.length();
    if (length != MAX_DIGITS) {
      throw new InvalidLengthException();
    }
  }

  private void validateSymbols(String currentNumber) {
    if (!currentNumber.matches("^\\d*$")) {
      throw new InvalidSymbolException();
    }
  }

  private void validateNoDuplicates(String currentNumber) {
    int length = currentNumber.length();
    for (int i = ZERO_INDEX; i < length; i++) {
      for (int j = ZERO_INDEX; j < length; j++) {
        if (i != j && currentNumber.charAt(i) == currentNumber.charAt(j)) {
          throw new DuplicateDigitException();
        }
      }
    }
  }
}
