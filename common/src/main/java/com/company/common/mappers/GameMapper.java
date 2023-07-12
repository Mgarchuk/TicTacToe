package com.company.common.mappers;

import com.company.common.dtos.GameDto;
import com.company.common.models.GameEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    GameEntity toEntity(GameDto dto);

    GameDto toDTO(GameEntity entity);
}
