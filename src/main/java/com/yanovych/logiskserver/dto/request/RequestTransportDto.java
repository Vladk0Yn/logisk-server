package com.yanovych.logiskserver.dto.request;

import com.yanovych.logiskserver.domain.TransportType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record RequestTransportDto(
        Long id,
        String name,
        TransportType type,
        String code,
        Double loadCapacity,
        Double loadHeight,
        Double loadWidth
) {
}
