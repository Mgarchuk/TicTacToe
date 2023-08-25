package com.company.battle.mappers;

import com.company.common.dtos.GameDto;
import com.company.common.models.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper( GameMapper.class );

    GameEntity toEntity(GameDto dto);

    GameDto toDTO(GameEntity entity);
}
