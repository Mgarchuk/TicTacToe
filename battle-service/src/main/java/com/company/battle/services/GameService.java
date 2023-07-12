package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.common.models.GameEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import com.company.common.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public GameEntity getById(UUID id) {
        return gameRepository.findById(id).orElse(null);
    }

    public GameEntity getByLink(String link) {
        Optional<GameEntity> gameEntity = gameRepository.findByLink(link);
        return gameEntity.orElse(null);
    }

    public List<GameEntity> getPublicGames() {
        return gameRepository.findByVisibility(GameVisibility.PUBLIC);
    }

    public GameEntity getByParameters(int squareSize, int linesCountForWin, int moveTimeLimit) {
        Optional<GameEntity> gameEntity = gameRepository.findTop1ByVisibilityAndSquareSizeAndLinesForWinAndMoveTimeLimitOrderByCreationDateAsc(GameVisibility.PUBLIC, squareSize, linesCountForWin, moveTimeLimit);
        return gameEntity.orElse(null);
    }

    public GameEntity create(GameEntity gameEntity) {

        if (gameEntity.getSettings() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Settings cannot be null");
        }

        //ToDo: generate normal link
        gameEntity.setLink(UUID.randomUUID().toString());
        gameEntity.setStatus(GameStatus.PENDING);

        return gameRepository.save(gameEntity);
    }

    public GameEntity leave(GameEntity gameEntity, UUID userId) {
        if (gameEntity.getStatus() == GameStatus.ACTIVE) {
            UUID winnerId = userId == gameEntity.getSettings().getXPlayerId() ? gameEntity.getSettings().getOPlayerId() : userId;
            Optional<UserEntity> winner = userRepository.findById(winnerId);

            gameEntity.setWinner(winner.orElse(null));
        }

        gameEntity.setStatus(GameStatus.FINISHED);

        return gameRepository.save(gameEntity);
    }
}
