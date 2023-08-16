package com.company.battle.utils.services;

import com.company.battle.utils.Coordinate;
import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.UserEntity;

import java.util.Map;
import java.util.UUID;

public class GameWinnerService {

    public static boolean checkWin(GameEntity game, MoveEntity lastMove, UserEntity userEntity, Map<Coordinate, UUID> movesMap) {
        Coordinate lastCoordinate = new Coordinate(lastMove.getDescription());

        int squareSize = game.getSettings().getSquareSize();
        int linesCountForWin = game.getSettings().getLinesCountForWin();

        UUID playerId = userEntity.getId();

        return checkHorizontalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkVerticalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkLeftDiagonalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkRightDiagonalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize);
    }

    private static boolean checkCoordinate(Coordinate nextCoordinate, int squareSize, UUID userId, Map<Coordinate, UUID> movesMap) {
        return nextCoordinate.getX() >= 0 && nextCoordinate.getX() < squareSize
                && nextCoordinate.getY() >= 0 && nextCoordinate.getY() < squareSize
                && userId.equals(movesMap.get(nextCoordinate));
    }

    private static boolean checkHorizontalCoordinates(UUID userId, Map<Coordinate, UUID> movesMap, int linesCountForWin, Coordinate lastCoordinate, int squareSize) {
        int lineCount = 1;
        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() + i, lastCoordinate.getY());
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() - i, lastCoordinate.getY());
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }
        return lineCount >= linesCountForWin;
    }


    private static boolean checkVerticalCoordinates(UUID userId, Map<Coordinate, UUID> movesMap, int linesCountForWin, Coordinate lastCoordinate, int squareSize) {
        int lineCount = 1;
        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX(), lastCoordinate.getY() + i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX(), lastCoordinate.getY() - i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }
        return lineCount >= linesCountForWin;
    }

    private static boolean checkLeftDiagonalCoordinates(UUID userId, Map<Coordinate, UUID> movesMap, int linesCountForWin, Coordinate lastCoordinate, int squareSize) {
        int lineCount = 1;
        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() + i, lastCoordinate.getY() + i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() - i, lastCoordinate.getY() - i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }
        return lineCount >= linesCountForWin;
    }


    private static boolean checkRightDiagonalCoordinates(UUID userId, Map<Coordinate, UUID> movesMap, int linesCountForWin, Coordinate lastCoordinate, int squareSize) {
        int lineCount = 1;
        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() + i, lastCoordinate.getY() - i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < linesCountForWin; i++) {
            Coordinate nextCoordinate = new Coordinate(lastCoordinate.getX() - i, lastCoordinate.getY() + i);
            if (checkCoordinate(nextCoordinate, squareSize, userId, movesMap)) {
                lineCount += 1;
            } else {
                break;
            }
        }

        return lineCount >= linesCountForWin;
    }
}
