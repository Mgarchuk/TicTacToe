package com.company.battle.mappers;

import com.company.battle.repositories.GameRepository;
import com.company.common.dtos.MoveDto;
import com.company.common.models.MoveEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MoveMapper {

    @Autowired
    protected GameRepository gameRepository;

    public static MoveMapper INSTANCE = Mappers.getMapper(MoveMapper.class);

    @Mapping(target = "game", expression = "java(gameRepository.findById(UUID.fromString(dto.getGameId())).orElse(null))")
    public abstract MoveEntity toEntity(MoveDto dto);

    @Mapping(target = "gameId", source = "entity.game.id")
    public abstract MoveDto toDTO(MoveEntity entity);
}
