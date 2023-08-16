package com.company.battle.controllers;

import com.company.battle.mappers.MoveMapper;
import com.company.battle.services.MoveService;
import com.company.common.dtos.AddMoveRequestDto;
import com.company.common.dtos.MoveDto;
import com.company.common.models.MoveEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/game/{gameId}")
@RequiredArgsConstructor
public class MoveController {

    @Autowired
    private final MoveService moveService;

    @Autowired
    private final MoveMapper moveMapper;

    @GetMapping("/moves")
    public List<MoveDto> getHistoryByGameId(@PathVariable UUID gameId) {
        List<MoveEntity> moveEntities = moveService.getByGameId(gameId);
        return moveEntities.stream()
                .map(moveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/move")
    public MoveDto addMove(@Valid @RequestBody AddMoveRequestDto addMoveRequestDto, @PathVariable UUID gameId) {
        MoveEntity moveEntity = moveService.add(addMoveRequestDto, gameId);
        return moveMapper.toDTO(moveEntity);
    }
}
