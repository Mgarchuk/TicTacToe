package com.company.battle.mappers;

import com.company.battle.repositories.GameRepository;
import com.company.common.dtos.MoveDto;
import com.company.common.models.MoveEntity;
import com.company.common.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MoveMapper {

    @Autowired
    protected GameRepository gameRepository;

    @Autowired
    protected UserRepository userRepository;

    public static MoveMapper INSTANCE = Mappers.getMapper(MoveMapper.class);

    @Mapping(target = "game", expression = "java(gameRepository.getById(UUID.fromString(dto.getGameId())))")
    @Mapping(target = "user", expression = "java(userRepository.getById(UUID.fromString(dto.getUserId())))")
    public abstract MoveEntity toEntity(MoveDto dto);

    @Mapping(target = "userId", source = "entity.user.id")
    @Mapping(target = "gameId", source = "entity.game.id")
    public abstract MoveDto toDTO(MoveEntity entity);
}
