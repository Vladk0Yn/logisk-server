package com.yanovych.logiskserver.service.driver;

import com.yanovych.logiskserver.domain.OrderStatus;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;

import java.util.List;

public interface DriverOrderService {
    List<ResponseOrderDto> getAvailable();
    List<ResponseOrderDto> getActiveOrders();
    ResponseOrderDto get(Long id);
    ResponseOrderDto take(Long id);
    ResponseOrderDto updateOrderStatus(Long id, OrderStatus status);
}
