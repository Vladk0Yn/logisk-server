package com.yanovych.logiskserver.dto.response;

import java.math.BigDecimal;

public record ResponseClientDto (
    Long id,
    String name,
    String phoneNumber,
    String email
) {

}
