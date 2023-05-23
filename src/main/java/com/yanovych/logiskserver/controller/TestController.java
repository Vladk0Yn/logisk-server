package com.yanovych.logiskserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@Tag(name = "/test", description = "test description")
public class TestController {

    @Operation(summary = "Test description")
    @GetMapping("/test")
    public String test() {
        return "test api";
    }
}
