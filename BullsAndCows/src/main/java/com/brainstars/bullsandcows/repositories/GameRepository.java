package com.brainstars.bullsandcows.repositories;

import java.util.List;

import com.brainstars.bullsandcows.models.Game;
import com.brainstars.bullsandcows.models.dtos.UsersResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Integer> {
  @Query("FROM Game g  where g.username =:username")
  List<Game> getAllUserGames(String username);

  @Query(value = "select g.username as username, count(g.finished) as numberOfFinishedGames, min(g.times_played) as bestTimesPlayed,  min(extract(minute from (g.last_modified_date - g.created_date))) as bestTimeInMinutes from games g where g.finished=true group by g.username order by min(g.times_played)", nativeQuery = true)
  List<UsersResponse> findAllByMinTimesPlayedAndMinDateDifference();
}
