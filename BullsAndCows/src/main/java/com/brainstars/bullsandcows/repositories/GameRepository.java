package com.brainstars.bullsandcows.repositories;

import com.brainstars.bullsandcows.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("FROM Game a  where a.username =:username")
    List<Game> getAllUserGames(String username);
}
