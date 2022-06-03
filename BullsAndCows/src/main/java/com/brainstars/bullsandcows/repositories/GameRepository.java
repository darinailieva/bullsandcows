package com.brainstars.bullsandcows.repositories;

import com.brainstars.bullsandcows.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("FROM Game g  where g.username =:username")
    List<Game> getAllUserGames(String username);

    @Query(value = "select g.username, count(g.finished), min(g.times_played), min(datediff(minute,g.created_date, g.last_modified_date)) from games " +
            "g where g.finished=true group by g.username order by min(g.times_played) and min(datediff(minute,g.created_date, g.last_modified_date))", nativeQuery = true)
    List<Object []> findAllByMinTimesPlayedAndMinDateDifference();
}
