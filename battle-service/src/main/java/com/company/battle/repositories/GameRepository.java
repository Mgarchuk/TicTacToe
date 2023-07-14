package com.company.battle.repositories;

import com.company.common.models.GameEntity;
import com.company.common.models.enums.GameVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    Optional<GameEntity> findByLink(String link);

    //ToDo: add sort by date and limit 1
    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize and g.lines_count_for_win = :linesCount and g.move_time_limit = :timeLimit and g.status = 'PENDING' " +
            "and g.visibility = 'PUBLIC' and g.o_player_id is not null",
            nativeQuery = true)
    Optional<GameEntity> findGameForXPlayer(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);

    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize and g.lines_count_for_win = :linesCount and g.move_time_limit = :timeLimit and g.status = 'PENDING' " +
            "and g.visibility = 'PUBLIC' and g.x_player_id is not null",
            nativeQuery = true)
    Optional<GameEntity> findGameForOPlayer(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);

    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize and g.lines_count_for_win = :linesCount and g.move_time_limit = :timeLimit and g.status = 'PENDING' " +
            "and g.visibility = 'PUBLIC'",
            nativeQuery = true)
    Optional<GameEntity> findGame(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);
    List<GameEntity> findByVisibility(GameVisibility visibility);
}
