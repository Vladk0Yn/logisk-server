package com.yanovych.logiskserver.dto.response;

import com.yanovych.logiskserver.domain.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ResponseOrderDto(
        Long id,
        String name,
        Double weight,
        Double height,
        Double length,
        Double width,
        OrderType type,
        OrderStatus status,
        BigDecimal deliveryPrice,
        Timestamp createdTime,
        Timestamp deliverDueTime,
        ResponseLocationDto locationTo,
        ResponseLocationDto locationFrom,
        Long clientId,
        Long driverId
) {

}
