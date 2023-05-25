package com.yanovych.logiskserver.dto.response;

import java.math.BigDecimal;

public record ResponseUserDto(
        Long id,
        String email,
        String password,
        String name,
        BigDecimal balance,
        String phoneNumber,
        String role
) {
}
