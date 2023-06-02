package com.yanovych.logiskserver.service.implementation.driver;

import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.domain.OrderStatus;
import com.yanovych.logiskserver.domain.Transport;
import com.yanovych.logiskserver.dto.mapper.ResponseClientDtoMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseDriverDtoMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseOrderDtoMapper;
import com.yanovych.logiskserver.dto.response.ResponseClientDto;
import com.yanovych.logiskserver.dto.response.ResponseDriverDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.repository.OrderRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.driver.DriverOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverOrderServiceImpl implements DriverOrderService {

    private final OrderRepository orderRepository;
    private final SessionUserService sessionUserService;

    private List<Order> getAvailableOrders() {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        if (driver == null) {
            return null;
        }
        Transport transport = driver.getTransport();
        if (transport == null) {
            return null;
        }
        return this.orderRepository
                .findOrdersByStatusAndWidthLessThanAndWeightLessThanAndHeightLessThanOrderByCreatedTimeDesc
                        (OrderStatus.CREATED, transport.getLoadWidth(), transport.getLoadCapacity(), transport.getLoadHeight());
    }

    private List<Order> getDriverOrders() {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        if (driver == null) {
            return null;
        }
        List<Order> orders = this.orderRepository.findOrdersByDriver_IdOrderByDeliverDueTimeDesc(driver.getId());
        if (orders == null || orders.isEmpty()) {
            return null;
        }
        return orders;
    }

    @Override
    public List<ResponseOrderDto> getAvailable() {
        List<Order> availableOrders = getAvailableOrders();
        if (availableOrders == null || availableOrders.isEmpty()) {
            return null;
        }
        return availableOrders.stream()
                .map(ResponseOrderDtoMapper.INSTANCE::orderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseOrderDto> getActive() {
        List<Order> orders = getDriverOrders();
        if (orders == null || orders.isEmpty()) {
            return null;
        }
        return orders.stream()
                .map(ResponseOrderDtoMapper.INSTANCE::orderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDto get(Long id) {
        return ResponseOrderDtoMapper.INSTANCE.orderToDto(this.orderRepository.getReferenceById(id));
    }

    @Override
    public ResponseOrderDto take(Long id) {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        List<Order> availableOrders = getAvailableOrders();
        if (availableOrders == null || availableOrders.isEmpty()) {
            return null;
        }
        Order order = orderRepository.getReferenceById(id);
        if (!availableOrders.contains(order)) {
            return null;
        }
        order.setStatus(OrderStatus.ACCEPTED);
        order.setDriver(driver);
        return ResponseOrderDtoMapper.INSTANCE.orderToDto(this.orderRepository.save(order));
    }

    @Override
    public ResponseOrderDto updateOrderStatus(Long id, String status) {
        List<Order> orders = getDriverOrders();
        if (orders == null) {
            return null;
        }
        Order order = orders.stream()
                .filter(o -> Objects.equals(o.getId(), id)).findFirst().orElse(null);
        if (order == null) {
            return null;
        }
        switch (status) {
            case "DELIVERING" -> {
                order.setStatus(OrderStatus.DELIVERING);
            }
            case "FINISHED" -> {
                order.setStatus(OrderStatus.FINISHED);
                order.setFinishTime(new Timestamp(System.currentTimeMillis()));
                order.getDriver().getUser()
                        .setBalance(
                                order.getDriver().getUser().getBalance().add(order.getDeliveryPrice()));
            }
            case "CANCELED" -> {
                order.setStatus(OrderStatus.CANCELED);
                order.setDriver(null);
            }
        }
        return ResponseOrderDtoMapper.INSTANCE.orderToDto(this.orderRepository.save(order));
    }

    @Override
    public ResponseClientDto getClient(Long id) {
        Order order = this.orderRepository.getReferenceById(id);
        return ResponseClientDtoMapper.INSTANCE.clientToDto(order.getClient());
    }
}
