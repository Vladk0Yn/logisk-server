package com.yanovych.logiskserver.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yanovych.logiskserver.domain.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public record ResponseOrderDto(
        Long id,
        String name,
        Double weight,
        Double height,
        Double width,
        OrderType type,
        OrderStatus status,
        BigDecimal deliveryPrice,
        Timestamp createdTime,
        Timestamp deliverDueTime,
        Long locationToId,
        Long locationFromId,
        Long clientId
) {

}
