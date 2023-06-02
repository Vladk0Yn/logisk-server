package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Transport;
import com.yanovych.logiskserver.dto.request.RequestTransportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestTransportDtoMapper {
    RequestTransportDtoMapper INSTANCE = Mappers.getMapper(RequestTransportDtoMapper.class);

    Transport dtoToTransport(RequestTransportDto dto);
}
