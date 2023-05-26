package com.yanovych.logiskserver.dto.request;

import com.yanovych.logiskserver.domain.LocationType;

public record RequestLocationDto(
        Long id,
        String name,
        LocationType type,
        Double longitude,
        Double latitude
) {
}
