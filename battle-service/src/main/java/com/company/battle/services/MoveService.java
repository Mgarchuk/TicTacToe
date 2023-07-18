package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.repositories.MoveRepository;
import com.company.battle.utils.GameUtils;
import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.enums.GameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoveService {

    //ToDo: byte to int

    @Autowired
    private final MoveRepository moveRepository;

    @Autowired
    private final GameRepository gameRepository;

    //ToDo: check gameEntity -- null and add in mapper converting gameId to Game
    //ToDO: check or save gameRepository all: game and moves
    public MoveEntity add(MoveEntity moveEntity) {
        moveEntity.setCreationDate(LocalDateTime.now());
        moveEntity = moveRepository.save(moveEntity);
        //ToDo: add check on existing move
        //ToDo: check saving moves in gameEntity;
        gameRepository.save(moveEntity.getGame());

        if (GameUtils.checkWin(moveEntity, moveEntity.getUser())) {
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
