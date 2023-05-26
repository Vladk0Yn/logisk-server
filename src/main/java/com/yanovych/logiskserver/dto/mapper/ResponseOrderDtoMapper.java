package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseOrderDtoMapper {
    ResponseOrderDtoMapper INSTANCE = Mappers.getMapper(ResponseOrderDtoMapper.class);

    @Mapping(source = "locationTo.id", target = "locationToId")
    @Mapping(source = "locationFrom.id", target = "locationFromId")
    @Mapping(source = "client.id", target = "clientId")
    ResponseOrderDto orderToDto(Order order);
}
