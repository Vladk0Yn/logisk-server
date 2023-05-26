package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Location;
import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.request.RequestLocationDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestLocationDtoMapper {
    RequestLocationDtoMapper INSTANCE = Mappers.getMapper(RequestLocationDtoMapper.class);

    Location dtoToLocation(RequestLocationDto locationDto);
}
