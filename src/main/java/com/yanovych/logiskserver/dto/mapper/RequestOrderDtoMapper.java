package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestOrderDtoMapper {
    RequestOrderDtoMapper INSTANCE = Mappers.getMapper(RequestOrderDtoMapper.class);

    @Mapping(source = "locationToId", target = "locationTo.id")
    @Mapping(source = "locationFromId", target = "locationFrom.id")
    Order dtoToOrder(RequestOrderDto dto);
}