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

    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize AND g.lines_count_for_win = :linesCount AND g.move_time_limit = :timeLimit AND g.status = 'PENDING' " +
            "AND g.visibility = 'PUBLIC' and g.o_player_id IS NOT NULL ORDER BY creation_date LIMIT 1",
            nativeQuery = true)
    Optional<GameEntity> findGameForXPlayer(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);

    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize AND g.lines_count_for_win = :linesCount AND g.move_time_limit = :timeLimit AND g.status = 'PENDING' " +
            "AND g.visibility = 'PUBLIC' and g.x_player_id IS NOT NULL ORDER BY creation_date LIMIT 1",
            nativeQuery = true)
    Optional<GameEntity> findGameForOPlayer(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);

    @Query(value = "SELECT * FROM game g WHERE g.square_size = :squareSize AND g.lines_count_for_win = :linesCount AND g.move_time_limit = :timeLimit AND g.status = 'PENDING' " +
            "AND g.visibility = 'PUBLIC' ORDER BY creation_date LIMIT 1",
            nativeQuery = true)
    Optional<GameEntity> findGame(@Param("squareSize") int squareSize, @Param("linesCount") int linesCountForWin, @Param("timeLimit") int moveTimeLimit);

    List<GameEntity> findByVisibility(GameVisibility visibility);
}
