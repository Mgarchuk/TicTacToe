package com.company.battle.mappers;

import com.company.common.dtos.SettingsDto;
import com.company.common.models.SettingsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingsMapper {

    SettingsMapper INSTANCE = Mappers.getMapper(SettingsMapper.class);

    SettingsEntity toEntity(SettingsDto dto);

    SettingsDto toDTO(SettingsEntity entity);
}
