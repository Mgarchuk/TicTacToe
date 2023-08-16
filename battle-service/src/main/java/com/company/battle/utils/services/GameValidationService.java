package com.company.battle.utils.services;

import com.company.battle.utils.Coordinate;
import com.company.common.dtos.CreateGameRequestDto;
import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.enums.GameStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

public class GameValidationService {

    public static boolean isValidMove(GameEntity game, MoveEntity move, Map<Coordinate, UUID> movesMap) {
        String[] moveData = move.getDescription().split(";");
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

        return (movesMap.size() % 2 == 0 && move.getUser().getId().equals(game.getSettings().getXPlayerId()))
                || (movesMap.size() % 2 == 1 && move.getUser().getId().equals(game.getSettings().getOPlayerId()));
    }

    public static boolean isValidGameToCreate(CreateGameRequestDto createGameRequestDto) {
        return createGameRequestDto.getLinesCountForWin() <= createGameRequestDto.getSquareSize();
    }

    public static void validGameToAddingMove(GameEntity game) {
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't find game by gameId");
        } else if (game.getStatus().equals(GameStatus.PENDING)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game didn't start");
        } else if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game is over");
        }
    }

    public static void validGameToJoinGame(GameEntity game, UUID xPlayerId, UUID oPlayerId) {
        if (game.getStatus() != GameStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game status must be PENDING");
        }
        if (xPlayerId != null && oPlayerId != null || xPlayerId == null && oPlayerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нou can't connect to the game because there must already be 1 member in the game");
        }
    }

    public static void validGameToLeaveGame(GameEntity game, UUID userId) {
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no game with this id");
        } else if (game.getStatus() == GameStatus.FINISHED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There game is already over");
        } else if (!game.getSettings().getXPlayerId().equals(userId) && !game.getSettings().getOPlayerId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not a member of this game");
        }
    }
}
