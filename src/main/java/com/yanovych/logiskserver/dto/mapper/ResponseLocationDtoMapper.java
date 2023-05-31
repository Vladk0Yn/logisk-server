package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Location;
import com.yanovych.logiskserver.dto.response.ResponseLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseLocationDtoMapper {
    ResponseLocationDtoMapper INSTANCE = Mappers.getMapper(ResponseLocationDtoMapper.class);

    ResponseLocationDto locationToDto(Location location);
}
