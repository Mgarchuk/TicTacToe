package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.utils.services.GameValidationService;
import com.company.common.dtos.CreateGameRequestDto;
import com.company.common.models.GameEntity;
import com.company.common.models.SettingsEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import com.company.common.models.enums.PreferableSide;
import com.company.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public List<GameEntity> findAll(final Specification<GameEntity> specification) {
        return gameRepository.findAll(specification);
    }

    public List<GameEntity> getPublicGames() {
        return gameRepository.findByVisibility(GameVisibility.PUBLIC);
    }

    public GameEntity create(CreateGameRequestDto createGameRequestDto, UUID userId) {
        if (!GameValidationService.isValidGameToCreate(createGameRequestDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game to create");
        }

        GameEntity gameEntity = new GameEntity();
        SettingsEntity settingsEntity = new SettingsEntity();
        gameEntity.setSettings(settingsEntity);

        if (getPlayerSide(createGameRequestDto.getPreferableSide()) == PreferableSide.X) {
            settingsEntity.setXPlayerId(userId);
        } else {
            settingsEntity.setOPlayerId(userId);
        }
        settingsEntity.setSquareSize(createGameRequestDto.getSquareSize());
        settingsEntity.setMoveTimeLimit(createGameRequestDto.getMoveTimeLimit());
        settingsEntity.setLinesCountForWin(createGameRequestDto.getLinesCountForWin());

        gameEntity.setStatus(GameStatus.PENDING);
        gameEntity.setCreationDate(LocalDateTime.now());
        gameEntity.setVisibility(createGameRequestDto.getVisibility());

        return gameRepository.save(gameEntity);
    }

    public GameEntity joinGame(GameEntity gameEntity, UUID userId) {
        UUID xPlayerId = gameEntity.getSettings().getXPlayerId();
        UUID oPlayerId = gameEntity.getSettings().getOPlayerId();
        GameValidationService.validGameToJoinGame(gameEntity, xPlayerId, oPlayerId, userId);
        gameEntity.setStatus(GameStatus.ACTIVE);
        if (xPlayerId == null) {
            gameEntity.getSettings().setXPlayerId(userId);
        } else {
            gameEntity.getSettings().setOPlayerId(userId);
        }
        return gameRepository.save(gameEntity);
    }

    public GameEntity leave(UUID gameId, UUID userId) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElse(null);

        GameValidationService.validGameToLeaveGame(gameEntity, userId);

        UUID winnerId = userId.equals(gameEntity.getSettings().getXPlayerId()) ? gameEntity.getSettings().getOPlayerId() : gameEntity.getSettings().getXPlayerId();
        Optional<UserEntity> winner = userRepository.findById(winnerId);
        gameEntity.setWinner(winner.orElse(null));
        gameEntity.setStatus(GameStatus.FINISHED);

        return gameRepository.save(gameEntity);
    }

    private PreferableSide getPlayerSide(PreferableSide preferableSide) {
        if (preferableSide == PreferableSide.ANY) {
            Random random = new Random();
            return random.nextBoolean() ? PreferableSide.X : PreferableSide.O;
        }

        return preferableSide;
    }
}
