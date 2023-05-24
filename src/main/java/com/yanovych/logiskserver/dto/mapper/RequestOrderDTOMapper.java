package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestOrderDTOMapper {
    RequestOrderDTOMapper INSTANCE = Mappers.getMapper(RequestOrderDTOMapper.class);

    @Mapping(source = "locationToId", target = "locationTo.id")
    @Mapping(source = "locationFromId", target = "locationFrom.id")
    @Mapping(source = "clientId", target = "client.id")
    Order dtoToOrder(RequestOrderDto dto);
}