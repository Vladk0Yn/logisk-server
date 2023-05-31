package com.yanovych.logiskserver.dto.response;

import com.yanovych.logiskserver.domain.LocationType;

public record ResponseLocationDto(
        Long id,
        String name,
        LocationType type,
        Double longitude,
        Double latitude,
        String address
) {
}
