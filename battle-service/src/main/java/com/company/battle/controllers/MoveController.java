package com.company.battle.controllers;

import com.company.common.dtos.MoveDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/move")
public class MoveController {

    @GetMapping("/{gameId}")
    List<MoveDto> getHistoryByGameId(@PathVariable UUID gameId) {
        return null;
    }

    @PostMapping
    MoveDto addMove(@RequestBody MoveDto moveDto) {
        return null;
    }
}
