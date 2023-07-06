package com.company.battle.controllers;

import com.company.common.dtos.GameDto;
import com.company.common.dtos.SettingsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

    @GetMapping("/{id}")
    public GameDto getGameById(@PathVariable UUID id) {
        return null;
    }

    @GetMapping("/{link}")
    public GameDto getGameByLink(@PathVariable String link) {
        return null;
    }

    @GetMapping
    public List<GameDto> getActiveGames() {
        return null;
    }

    @PostMapping("/create")
    public GameDto createGame(@RequestBody GameDto gameDto) {
        return null;
    }

    @PutMapping("/join/{link}")
    public GameDto joinGame(@PathVariable String link) {
        return null;
    }

    @PutMapping("/leave")
    public GameDto leaveGame(@RequestBody GameDto gameDto) {
        return null;
    }
}
