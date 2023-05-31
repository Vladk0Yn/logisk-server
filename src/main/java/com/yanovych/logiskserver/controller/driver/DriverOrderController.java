package com.yanovych.logiskserver.controller.driver;

import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.service.driver.DriverOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver/orders")
@Tag(name = "/driver/orders", description = "Driver orders endpoint")
public class DriverOrderController {

    private final DriverOrderService driverOrderService;

    @GetMapping("/available")
    @Operation(summary = "get available orders for driver")
    public ResponseEntity<List<ResponseOrderDto>> getAvailableOrders() {
        List<ResponseOrderDto> orders = this.driverOrderService.getAvailable();
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/available/{id}/take")
    public ResponseEntity<ResponseOrderDto> takeOrder(@PathVariable Long id) {
        ResponseOrderDto order = this.driverOrderService.take(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
