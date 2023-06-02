package com.yanovych.logiskserver.service.driver;

import com.yanovych.logiskserver.dto.request.RequestTransportDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseTransportDto;

public interface TransportService {
    ResponseTransportDto create(RequestTransportDto requestTransportDto);
    ResponseTransportDto get();
    ResponseTransportDto update(RequestTransportDto requestTransportDto);
}
