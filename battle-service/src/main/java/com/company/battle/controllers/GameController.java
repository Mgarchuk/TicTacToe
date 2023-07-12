package com.company.battle.controllers;

import com.company.battle.services.GameService;
import com.company.common.dtos.GameDto;
import com.company.common.mappers.GameMapper;
import com.company.common.models.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @GetMapping("/{id}")
    public GameDto getGameById(@PathVariable UUID id) {
        GameEntity gameEntity = gameService.getById(id);

        //ToDo: check how it will be works with null
        return gameMapper.toDTO(gameEntity);
    }

    @GetMapping("by-link/{link}")
    public GameDto getGameByLink(@PathVariable String link) {
        GameEntity gameEntity = gameService.getByLink(link);
        return gameMapper.toDTO(gameEntity);
    }

    @GetMapping
    public List<GameDto> getPublicGames() {
        List<GameEntity> gameEntities = gameService.getPublicGames();
        return gameEntities.stream()
                .map(gameEntity -> gameMapper.toDTO(gameEntity))
                .collect(Collectors.toList());
    }

    @GetMapping
    public GameDto findGame(int squareSize, int linesCountForWin, int moveTimeLimit) {
        GameEntity gameEntity = gameService.getByParameters(squareSize, linesCountForWin, moveTimeLimit);
        return gameMapper.toDTO(gameEntity);
    }

    @PostMapping("/create")
    public GameDto createGame(@RequestBody GameDto gameDto) {
        GameEntity gameEntity = gameMapper.toEntity(gameDto);
        gameEntity = gameService.create(gameEntity);
        return gameMapper.toDTO(gameEntity);
    }

    @PutMapping("/join/{link}")
    public GameDto joinGame(@PathVariable String link) {
        GameEntity gameEntity = gameService.getByLink(link);
        return gameMapper.toDTO(gameEntity);
    }

    @PutMapping("/leave")
    public GameDto leaveGame(@RequestBody GameDto gameDto, @RequestBody UUID userId) {
        GameEntity gameEntity = gameMapper.toEntity(gameDto);
        //ToDo: change userId as parameter to userId from authorization
        gameEntity = gameService.leave(gameEntity, userId);
        return gameMapper.toDTO(gameEntity);
    }
}
