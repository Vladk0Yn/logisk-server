package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseOrderDtoMapper {
    ResponseOrderDtoMapper INSTANCE = Mappers.getMapper(ResponseOrderDtoMapper.class);

    @Mapping(source = "locationTo", target = "locationTo")
    @Mapping(source = "locationFrom", target = "locationFrom")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "driver.id", target = "driverId")
    ResponseOrderDto orderToDto(Order order);

    @Mapping(source = "locationTo", target = "locationTo")
    @Mapping(source = "locationFrom", target = "locationFrom")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "driverId", target = "driver.id")
    Order dtoToOrder(ResponseOrderDto dto);
}
