package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.User;
import com.yanovych.logiskserver.dto.response.ResponseUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseUserDTOMapper {
    ResponseUserDTOMapper INSTANCE = Mappers.getMapper(ResponseUserDTOMapper.class);
    ResponseUserDto userToDto(User user);
}
