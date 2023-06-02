package com.yanovych.logiskserver.controller.client;

import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseDriverDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.service.client.ClientOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client/orders")
@Tag(name = "/client/orders", description = "Client orders endpoint")
public class ClientOrderController {

    private final ClientOrderService clientOrderService;

    @PostMapping()
    @Operation(summary = "create new order from user")
    public ResponseEntity<ResponseOrderDto> createOrder(
            @RequestBody RequestOrderDto requestOrderDto) {
        ResponseOrderDto order = clientOrderService.create(requestOrderDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "get all user orders")
    public ResponseEntity<List<ResponseOrderDto>> getOrders() {
        List<ResponseOrderDto> orders = clientOrderService.getAll();
        if (orders == null || orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user order by id")
    public ResponseEntity<ResponseOrderDto> getOrder(@PathVariable Long id) {
        ResponseOrderDto order = clientOrderService.get(id);
        if (clientOrderService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("")
    @Operation(summary = "update order")
    public ResponseEntity<ResponseOrderDto> updateOrder(
            @RequestBody RequestOrderDto requestOrderDto) {
        ResponseOrderDto order = clientOrderService.update(requestOrderDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete order by id")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        if (clientOrderService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        clientOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/driver")
    public ResponseEntity<ResponseDriverDto> getOrderDriver(@PathVariable Long id) {
        ResponseDriverDto driver = clientOrderService.getDriver(id);
        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
