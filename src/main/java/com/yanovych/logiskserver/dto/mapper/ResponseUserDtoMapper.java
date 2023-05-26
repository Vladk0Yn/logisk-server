package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.User;
import com.yanovych.logiskserver.dto.response.ResponseUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseUserDtoMapper {
    ResponseUserDtoMapper INSTANCE = Mappers.getMapper(ResponseUserDtoMapper.class);

    @Mapping(source = "role.name", target = "role")
    ResponseUserDto userToDto(User user);
}
