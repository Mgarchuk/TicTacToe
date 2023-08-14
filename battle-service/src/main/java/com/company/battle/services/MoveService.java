package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.repositories.MoveRepository;
import com.company.battle.utils.Coordinate;
import com.company.battle.utils.services.GameValidationService;
import com.company.battle.utils.services.GameWinnerService;
import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.enums.GameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoveService {

    @Autowired
    private final MoveRepository moveRepository;

    @Autowired
    private final GameRepository gameRepository;

    public MoveEntity add(MoveEntity moveEntity, UUID gameId) {

        GameEntity game = gameRepository.findById(gameId).orElse(null);

        GameValidationService.validGameToAddingMove(game);

        Map<Coordinate, UUID> movesMap = new HashMap<>();
        for (MoveEntity move : game.getMoves()) {
            movesMap.put(new Coordinate(move.getDescription()), move.getUser().getId());
        }

        if (!GameValidationService.isValidMove(moveEntity, movesMap)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move");
        }

        moveEntity.setCreationDate(LocalDateTime.now());
        moveEntity = moveRepository.save(moveEntity);

        if (GameWinnerService.checkWin(moveEntity, moveEntity.getUser(), movesMap)) {
            GameEntity gameEntity = moveEntity.getGame();
            gameEntity.setStatus(GameStatus.FINISHED);
            gameEntity.setWinner(moveEntity.getUser());
            gameRepository.save(gameEntity);
        }
        return moveEntity;
    }

    public List<MoveEntity> getByGameId(UUID gameId) {
        return moveRepository.findByGameId(gameId);
    }
}
