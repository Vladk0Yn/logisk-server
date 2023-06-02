package com.yanovych.logiskserver.dto.mapper;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.dto.response.ResponseClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseClientDtoMapper {
    ResponseClientDtoMapper INSTANCE = Mappers.getMapper(ResponseClientDtoMapper.class);

    @Mapping(source = "client.user.name", target = "name")
    @Mapping(source = "client.user.email", target = "email")
    @Mapping(source = "client.user.phoneNumber", target = "phoneNumber")
    ResponseClientDto clientToDto(Client client);
}
