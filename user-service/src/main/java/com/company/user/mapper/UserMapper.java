package com.company.user.mapper;

import com.company.common.dtos.UserDto;
import com.company.user.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDTO(UserEntity entity);
}
