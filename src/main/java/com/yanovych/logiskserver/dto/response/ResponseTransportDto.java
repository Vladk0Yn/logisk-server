package com.yanovych.logiskserver.dto.response;

import com.yanovych.logiskserver.domain.TransportType;

public record ResponseTransportDto (
        Long id,
        String name,
        TransportType type,
        String code,
        Double loadCapacity,
        Double loadHeight,
        Double loadWidth
) {
}
