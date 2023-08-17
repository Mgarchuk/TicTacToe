package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.repositories.MoveRepository;
import com.company.battle.utils.Coordinate;
import com.company.battle.utils.services.GameValidationService;
import com.company.battle.utils.services.GameWinnerService;
import com.company.battle.utils.services.UserAuthorizationService;
import com.company.common.dtos.AddMoveRequestDto;
import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.UserEntity;
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

    public MoveEntity add(AddMoveRequestDto addMoveRequestDto, UUID gameId, UserEntity currentUser) {

        GameEntity game = gameRepository.findById(gameId).orElse(null);

        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't find game by gameId");
        } else if (game.getStatus().equals(GameStatus.PENDING)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game didn't start");
        } else if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game is over");
        }

        Map<Coordinate, UUID> movesMap = new HashMap<>();
        for (MoveEntity move : game.getMoves()) {
            movesMap.put(new Coordinate(move.getDescription()), move.getUser().getId());
        }

        if (!GameValidationService.isValidMove(game, currentUser, addMoveRequestDto, movesMap)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid move");
        }

        MoveEntity moveEntity = new MoveEntity();
        moveEntity.setDescription(addMoveRequestDto.getDescription());
        moveEntity.setGame(game);
        moveEntity.setUser(currentUser);
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
