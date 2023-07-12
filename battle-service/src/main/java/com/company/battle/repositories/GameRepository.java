package com.company.battle.repositories;

import com.company.common.models.GameEntity;
import com.company.common.models.enums.GameVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    Optional<GameEntity> findByLink(String link);

    //ToDo: change on query
    Optional<GameEntity> findTop1ByVisibilityAndSquareSizeAndLinesForWinAndMoveTimeLimitOrderByCreationDateAsc(GameVisibility visibility, int squareSize, int linesCountForWin, int moveTimeLimit);

    List<GameEntity> findByVisibility(GameVisibility visibility);
}
