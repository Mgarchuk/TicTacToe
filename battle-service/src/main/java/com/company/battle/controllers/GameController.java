package com.company.battle.controllers;

import com.company.battle.mappers.GameMapper;
import com.company.battle.mappers.SettingsMapper;
import com.company.battle.services.GameService;
import com.company.common.dtos.GameDto;
import com.company.common.dtos.SettingsDto;
import com.company.common.models.GameEntity;
import com.company.common.models.SettingsEntity;
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

    @GetMapping("by-link/{link}")
    public GameDto getGameByLink(@PathVariable String link) {
        GameEntity gameEntity = gameService.getByLink(link);
        return gameMapper.toDTO(gameEntity);
    }

    @GetMapping("/public-games")
    public List<GameDto> getPublicGames() {
        List<GameEntity> gameEntities = gameService.getPublicGames();
        return gameEntities.stream()
                .map(gameMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/find-game")
    public GameDto findGame(@RequestBody SettingsDto settingsDto) {
        SettingsEntity settingsEntity = settingsMapper.toEntity(settingsDto);
        GameEntity gameEntity = gameService.findGame(settingsEntity);
        return gameMapper.toDTO(gameEntity);
    }

    //ToDo: change userId as parameter to userId from authorization
    @PostMapping("/create")
    public GameDto createGame(@RequestBody GameDto gameDto,  @PathVariable UUID userId) {
        GameEntity gameEntity = gameMapper.toEntity(gameDto);
        gameEntity = gameService.create(gameEntity, userId);
        return gameMapper.toDTO(gameEntity);
    }

    @PutMapping("/join/{link}")
    public GameDto joinGame(@PathVariable String link, @PathVariable UUID userId) {
        GameEntity gameEntity = gameService.getByLink(link);
        gameEntity = gameService.joinGame(gameEntity, userId);
        return gameMapper.toDTO(gameEntity);
    }

    //ToDo: change userId as parameter to userId from authorization
    @PutMapping("/leave/{userId}")
    public GameDto leaveGame(@RequestBody GameDto gameDto, @PathVariable UUID userId) {
        GameEntity gameEntity = gameMapper.toEntity(gameDto);
        gameEntity = gameService.leave(gameEntity, userId);
        return gameMapper.toDTO(gameEntity);
    }
}
