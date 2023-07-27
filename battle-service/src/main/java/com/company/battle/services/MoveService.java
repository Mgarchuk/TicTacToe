package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.repositories.MoveRepository;
import com.company.battle.utils.Coordinate;
import com.company.battle.utils.GameUtils;
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

        if (gameId != moveEntity.getGame().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path variable gameId and gameId in move don't match");
        } else if (moveEntity.getGame().getStatus().equals(GameStatus.FINISHED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game is over");
        }

        Map<Coordinate, UUID> movesMap = new HashMap<>();
        for (MoveEntity move : moveEntity.getGame().getMoves()) {
            movesMap.put(new Coordinate(move.getDescription()), move.getUser().getId());
        }

        if (!GameUtils.isValidMove(moveEntity, movesMap)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move");
        }

        moveEntity.setCreationDate(LocalDateTime.now());
        moveEntity = moveRepository.save(moveEntity);
        moveEntity.getGame().getMoves().add(moveEntity);

        if (GameUtils.checkWin(moveEntity, moveEntity.getUser(), movesMap)) {
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
