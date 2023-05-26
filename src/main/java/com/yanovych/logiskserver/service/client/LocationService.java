package com.yanovych.logiskserver.service.client;

import com.yanovych.logiskserver.dto.request.RequestLocationDto;
import com.yanovych.logiskserver.dto.response.ResponseLocationDto;

import java.util.List;

public interface LocationService {
    ResponseLocationDto create(RequestLocationDto locationDto);
    List<ResponseLocationDto> getAll();
    ResponseLocationDto get(Long id);
    ResponseLocationDto update(RequestLocationDto locationDto);
    void delete(Long id);
}
