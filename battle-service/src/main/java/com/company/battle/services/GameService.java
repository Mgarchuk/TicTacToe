package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.utils.GameUtils;
import com.company.common.models.GameEntity;
import com.company.common.models.SettingsEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import com.company.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<GameEntity> getPublicGames() {
        return gameRepository.findByVisibility(GameVisibility.PUBLIC);
    }

    public GameEntity findGame(SettingsEntity settingsEntity) {

        Optional<GameEntity> gameEntity;
        if (settingsEntity.getOPlayerId() == null && settingsEntity.getXPlayerId() == null) {
            gameEntity = gameRepository.findGame(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        } else if (settingsEntity.getXPlayerId() != null && settingsEntity.getOPlayerId() == null) {
            gameEntity = gameRepository.findGameForXPlayer(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        } else if (settingsEntity.getXPlayerId() == null) {
            gameEntity = gameRepository.findGameForOPlayer(settingsEntity.getSquareSize(), settingsEntity.getLinesCountForWin(), settingsEntity.getMoveTimeLimit());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request: you set up 2 players, but need 0 or 1");
        }

        return gameEntity.orElse(null);
    }

    public GameEntity create(GameEntity gameEntity, UUID userId) {
        if (!GameUtils.isValidGame(gameEntity)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game");
        }

        gameEntity.setStatus(GameStatus.PENDING);
        gameEntity.setCreationDate(LocalDateTime.now());
        if (gameEntity.getSettings().getXPlayerId() == null && gameEntity.getSettings().getOPlayerId() == null) {
            setRandomRole(gameEntity.getSettings(), userId);
        }

        return gameRepository.save(gameEntity);
    }

    public GameEntity joinGame(GameEntity gameEntity, UUID userId) {
        gameEntity.setStatus(GameStatus.ACTIVE);
        UUID xPlayerId = gameEntity.getSettings().getXPlayerId();
        UUID oPlayerId = gameEntity.getSettings().getOPlayerId();
        if (xPlayerId != null && oPlayerId != null || xPlayerId == null && oPlayerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нou can't connect to the game because there must already be 1 member in the game");
        }
        if (xPlayerId == null) {
            gameEntity.getSettings().setXPlayerId(userId);
        } else {
            gameEntity.getSettings().setOPlayerId(userId);
        }
        return gameRepository.save(gameEntity);
    }

    public GameEntity leave(UUID gameId, UUID userId) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElse(null);

        if (gameEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no game with this id");
        } else if (gameEntity.getSettings().getXPlayerId() != userId && gameEntity.getSettings().getOPlayerId() != userId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not a member of this game");
        }

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
