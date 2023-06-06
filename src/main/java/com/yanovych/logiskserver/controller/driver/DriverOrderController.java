package com.yanovych.logiskserver.controller.driver;

import com.yanovych.logiskserver.domain.OrderStatus;
import com.yanovych.logiskserver.dto.response.ResponseClientDto;
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

    @GetMapping("")
    @Operation(summary = "get all driver orders")
    public ResponseEntity<List<ResponseOrderDto>> getOrders() {
        List<ResponseOrderDto> orders = this.driverOrderService.getActive();
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/available")
    @Operation(summary = "get available orders for driver")
    public ResponseEntity<List<ResponseOrderDto>> getAvailableOrders() {
        List<ResponseOrderDto> orders = this.driverOrderService.getAvailable();
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/available/{id}/take")
    @Operation(summary = "take client order by driver")
    public ResponseEntity<ResponseOrderDto> takeOrder(@PathVariable Long id) {
        ResponseOrderDto order = this.driverOrderService.take(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get order by id for driver")
    public ResponseEntity<ResponseOrderDto> getOrder(@PathVariable Long id) {
        ResponseOrderDto order = this.driverOrderService.get(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}/client")
    @Operation(summary = "get order client details for driver")
    public ResponseEntity<ResponseClientDto> getOrderClient(@PathVariable Long id) {
        ResponseClientDto client = this.driverOrderService.getClient(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("{id}/status")
    @Operation(summary = "edit order status")
    public ResponseEntity<ResponseOrderDto> setOrderStatus(@PathVariable Long id, @RequestParam("status") String status) {
        ResponseOrderDto order = this.driverOrderService.updateOrderStatus(id, status);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{id}/remove")
    @Operation(summary = "remove driver from order")
    public ResponseEntity<?> removeDriverFromOrder(@PathVariable Long id) {
        this.driverOrderService.removeDriver(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
