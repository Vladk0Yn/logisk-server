package com.yanovych.logiskserver.dto.response;

import java.math.BigDecimal;

public record ResponseDriverDto (
        Long id,
        String name,
        String email,
        String phoneNumber,
        ResponseTransportDto transport
) {
}
