package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.dto.response.ResponseDriverDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseDriverDtoMapper {
    ResponseDriverDtoMapper INSTANCE = Mappers.getMapper(ResponseDriverDtoMapper.class);

    @Mapping(source = "driver.user.name", target = "name")
    @Mapping(source = "driver.user.email", target = "email")
    @Mapping(source = "driver.user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "driver.user.balance", target = "balance")
    ResponseDriverDto driverToDto(Driver driver);
}
