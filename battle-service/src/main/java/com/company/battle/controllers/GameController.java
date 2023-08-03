package com.company.battle.controllers;

import com.company.battle.mappers.GameMapper;
import com.company.battle.mappers.SettingsMapper;
import com.company.battle.services.GameService;
import com.company.common.dtos.GameDto;
import com.company.common.dtos.SearchGameRequestDto;
import com.company.common.models.GameEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/game")
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private final GameService gameService;

    private final GameMapper gameMapper = GameMapper.INSTANCE;

    private final SettingsMapper settingsMapper = SettingsMapper.INSTANCE;

    @GetMapping("/{id}")
    public GameDto getGameById(@PathVariable UUID id) {
        GameEntity gameEntity = gameService.getById(id);
        return gameMapper.toDTO(gameEntity);
    }

    @GetMapping("/public-games")
    public List<GameDto> getPublicGames() {
        List<GameEntity> gameEntities = gameService.getPublicGames();
        return gameEntities.stream()
                .map(gameMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<GameDto> searchGames(@Valid @RequestBody SearchGameRequestDto requestDto) {
        List<GameEntity> gameEntities = gameService.findGame(requestDto);
        return gameEntities.stream()
                .map(gameMapper::toDTO)
                .collect(Collectors.toList());
    }

    //ToDo: change randomUUID to userId from authorization
    @PostMapping("/create")
    public GameDto createGame(@Valid @RequestBody GameDto gameDto) {
        GameEntity gameEntity = gameMapper.toEntity(gameDto);
        gameEntity = gameService.create(gameEntity, UUID.randomUUID());
        return gameMapper.toDTO(gameEntity);
    }

    //ToDo: Allow connection to only 1 game
    @PutMapping("/join/{id}")
    public GameDto joinGame(@PathVariable UUID id) {
        GameEntity gameEntity = gameService.getById(id);
        gameEntity = gameService.joinGame(gameEntity, UUID.randomUUID());
        return gameMapper.toDTO(gameEntity);
    }

    //ToDo: change userId as parameter to userId from authorization
    @PutMapping("/{gameId}/leave")
    public GameDto leaveGame(@PathVariable UUID gameId) {
        GameEntity gameEntity = gameService.leave(gameId, UUID.randomUUID());
        return gameMapper.toDTO(gameEntity);
    }
}
