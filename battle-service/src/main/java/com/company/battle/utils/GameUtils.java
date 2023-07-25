package com.company.battle.utils;

import com.company.common.models.GameEntity;
import com.company.common.models.MoveEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;

import java.util.Map;
import java.util.UUID;

public class GameUtils {

    public static boolean checkWin(MoveEntity lastMove, UserEntity userEntity, Map<Coordinate, UUID> movesMap) {
        Coordinate lastCoordinate = new Coordinate(lastMove.getDescription());

        int squareSize = lastMove.getGame().getSettings().getSquareSize();
        int linesCountForWin = lastMove.getGame().getSettings().getLinesCountForWin();

        UUID playerId = userEntity.getId();

        return checkHorizontalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkVerticalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkLeftDiagonalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize)
                || checkRightDiagonalCoordinates(playerId, movesMap, linesCountForWin, lastCoordinate, squareSize);
    }

    public static boolean isValidMove(MoveEntity move, Map<Coordinate, UUID> movesMap) {
        String[] moveData = move.getDescription().split(";");
        if (moveData.length != 2 || move.getGame() == null) {
            return false;
        }

        int x;
        int y;
        try {
            x = Integer.parseInt(moveData[0]);
            y = Integer.parseInt(moveData[1]);
            int squareSize = move.getGame().getSettings().getSquareSize();
            if (x < 0 || x >= squareSize || y < 0 || y >= squareSize)
                return false;
        } catch (NumberFormatException ex) {
            return false;
        }

        return movesMap.get(new Coordinate(x, y)) == null;
    }

    public static boolean isValidGame(GameEntity game) {
        return game.getSettings().getLinesCountForWin() <= game.getSettings().getSquareSize()
                && (game.getStatus() == GameStatus.PENDING || game.getStatus() == null)
                && game.getWinner() == null;
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
