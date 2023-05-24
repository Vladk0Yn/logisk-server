package com.yanovych.logiskserver.controller.client;

import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.service.client.ClientOrderService;
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

    private final ClientOrderService orderService;

    @PostMapping()
    public ResponseEntity<ResponseOrderDto> createOrder(
            @RequestBody
            RequestOrderDto requestOrderDto) {
        ResponseOrderDto order = orderService.create(requestOrderDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseOrderDto>> getOrders() {
        List<ResponseOrderDto> orders = orderService.getAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDto> getOrder(@PathVariable Long id) {
        ResponseOrderDto order = orderService.get(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
