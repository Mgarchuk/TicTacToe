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
    GameDto getGameById(@PathVariable UUID id) {
        return null;
    }

    @GetMapping("/{link}")
    GameDto getGameByLink(@PathVariable String link) {
        return null;
    }

    @GetMapping
    List<GameDto> getActiveGames() {
        return null;
    }

    @PostMapping("/create")
    GameDto createGame(@RequestBody GameDto gameDto) {
        return null;
    }

    @PutMapping("/join")
    GameDto joinGame(@RequestBody String link) {
        return null;
    }

    @PutMapping("/leave")
    GameDto leaveGame(@RequestBody GameDto gameDto) {
        return null;
    }
}
