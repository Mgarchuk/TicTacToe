package com.company.battle.utils.services;

import com.company.battle.utils.Coordinate;
import com.company.common.dtos.AddMoveRequestDto;
import com.company.common.models.GameEntity;
import com.company.common.models.enums.GameStatus;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class GameValidationService {

    public static boolean isValidMove(GameEntity game, UUID userId, AddMoveRequestDto addMoveRequestDto, Map<Coordinate, UUID> movesMap) {
        String[] moveData = addMoveRequestDto.getDescription().split(";");
        if (moveData.length != 2) {
            return false;
        }

        int x, y;
        try {
            x = Integer.parseInt(moveData[0]);
            y = Integer.parseInt(moveData[1]);
            int squareSize = game.getSettings().getSquareSize();
            if (x < 0 || x >= squareSize || y < 0 || y >= squareSize)
                return false;
        } catch (NumberFormatException ex) {
            return false;
        }

        if (movesMap.get(new Coordinate(x, y)) != null) {
            return false;
        }

        return (movesMap.size() % 2 == 0 && userId.equals(game.getSettings().getXPlayerId()))
                || (movesMap.size() % 2 == 1 && userId.equals(game.getSettings().getOPlayerId()));
    }

    public static boolean isValidGameToCreate(GameEntity game) {
        return game.getSettings().getLinesCountForWin() <= game.getSettings().getSquareSize()
                && (game.getStatus() == GameStatus.PENDING || game.getStatus() == null)
                && game.getWinnerId() == null;
    }
}
