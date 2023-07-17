package com.company.battle.controllers;

import com.company.battle.mappers.MoveMapper;
import com.company.battle.services.MoveService;
import com.company.common.dtos.MoveDto;
import com.company.common.models.MoveEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/move")
@RequiredArgsConstructor
public class MoveController {

    @Autowired
    private final MoveService moveService;

    private final MoveMapper moveMapper = MoveMapper.INSTANCE;

    @GetMapping("/{gameId}")
    public List<MoveDto> getHistoryByGameId(@PathVariable UUID gameId) {
        List<MoveEntity> moveEntities = moveService.getByGameId(gameId);
        return moveEntities.stream()
                .map(moveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public MoveDto addMove(@RequestBody MoveDto moveDto) {
        //ToDo: add move validation
        MoveEntity moveEntity = moveMapper.toEntity(moveDto);
        moveEntity = moveService.add(moveEntity);
        return moveMapper.toDTO(moveEntity);
    }
}
