package com.yanovych.logiskserver.service.client;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;

import java.util.List;

public interface ClientOrderService {
    ResponseOrderDto create(RequestOrderDto order);
    List<ResponseOrderDto> getAll();
    ResponseOrderDto get(Long id);
}
