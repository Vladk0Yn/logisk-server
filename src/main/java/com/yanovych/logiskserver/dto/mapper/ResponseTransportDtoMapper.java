package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Transport;
import com.yanovych.logiskserver.dto.response.ResponseTransportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseTransportDtoMapper {
    ResponseTransportDtoMapper INSTANCE = Mappers.getMapper(ResponseTransportDtoMapper.class);

    ResponseTransportDto transportToDto(Transport transport);
}
