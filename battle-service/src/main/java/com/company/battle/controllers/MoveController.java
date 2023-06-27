package com.company.battle.controllers;

import com.company.common.dtos.MoveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/move")
public class MoveController {

    @GetMapping("/{moveId}")
    ResponseEntity<MoveDto> getMoveByMoveId(@PathVariable UUID moveId) {
        return null;
    }

    @GetMapping("/{gameId}")
    ResponseEntity<List<MoveDto>> getHistoryByGameId(@PathVariable UUID gameId) {
        return null;
    }

    @PostMapping
    ResponseEntity<MoveDto> addMove(@RequestBody MoveDto moveDto) {
        return null;
    }
}
