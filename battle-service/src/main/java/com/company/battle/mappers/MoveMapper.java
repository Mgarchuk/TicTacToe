package com.company.battle.mappers;

import com.company.common.dtos.MoveDto;
import com.company.common.models.MoveEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MoveMapper {

    MoveMapper INSTANCE = Mappers.getMapper(MoveMapper.class);

    MoveEntity toEntity(MoveDto dto);

    MoveDto toDTO(MoveEntity entity);
}
