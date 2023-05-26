package com.yanovych.logiskserver.dto.request;

import com.yanovych.logiskserver.domain.OrderStatus;
import com.yanovych.logiskserver.domain.OrderType;

import java.math.BigDecimal;
import java.sql.Timestamp;


public record RequestOrderDto(
        Long id,
        String name,
        Double weight,
        Double height,
        Double width,
        OrderType type,
        OrderStatus status,
        BigDecimal deliveryPrice,
        Timestamp deliverDueTime,
        Long locationToId,
        Long locationFromId
) {

}
