package com.brainstars.bullsandcows.repositories;

import com.brainstars.bullsandcows.models.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
}
