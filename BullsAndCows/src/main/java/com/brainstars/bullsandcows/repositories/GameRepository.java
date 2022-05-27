package com.brainstars.bullsandcows.repositories;

import com.brainstars.bullsandcows.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
