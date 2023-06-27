package com.company.battle.controllers;

import com.company.common.dtos.GameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

    @GetMapping("/{id}")
    ResponseEntity<GameDto> getGameById(@PathVariable UUID id) {
        return null;
    }

    @GetMapping
    ResponseEntity<List<GameDto>> getActiveGames() {
        return null;
    }

    @PostMapping
    ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        return null;
    }

    @PutMapping
    ResponseEntity<GameDto> leaveGame(@RequestBody GameDto gameDto) {
        return null;
    }
}
