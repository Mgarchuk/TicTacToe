package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.common.models.GameEntity;
import com.company.common.models.SettingsEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import com.company.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final UserRepository userRepository;

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

    public GameEntity findGame(SettingsEntity settingsEntity) {

        Optional<GameEntity> gameEntity;
        if (settingsEntity.getOPlayerId() == null && settingsEntity.getXPlayerId() == null) {
            gameEntity = gameRepository.findGame(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        } else if (settingsEntity.getXPlayerId() != null) {
            gameEntity = gameRepository.findGameForXPlayer(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        } else {
            gameEntity = gameRepository.findGameForOPlayer(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        }

        return gameEntity.orElse(null);
    }

    public GameEntity create(GameEntity gameEntity, UUID userId) {
        //ToDo: generate normal link
        gameEntity.setLink(UUID.randomUUID().toString());
        gameEntity.setStatus(GameStatus.PENDING);
        gameEntity.setCreationDate(LocalDateTime.now());
        if (gameEntity.getSettings().getXPlayerId() == null && gameEntity.getSettings().getOPlayerId() == null) {
            setRandomRole(gameEntity.getSettings(), userId);
        }

        return gameRepository.save(gameEntity);
    }

    public GameEntity joinGame(GameEntity gameEntity, UUID userId) {
        gameEntity.setStatus(GameStatus.ACTIVE);

        //ToDo: set role
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

    private void setRandomRole(SettingsEntity settingsEntity, UUID userId) {
        Random random = new Random();
        boolean isXPlayer = random.nextBoolean();
        if (isXPlayer) {
            settingsEntity.setXPlayerId(userId);
        } else {
            settingsEntity.setOPlayerId(userId);
        }
    }
}
