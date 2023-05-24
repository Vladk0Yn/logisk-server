package com.yanovych.logiskserver.dto.request;

public record RequestRegisterDto(
        String email,
        String name,
        String password,
        String role
) {
}
