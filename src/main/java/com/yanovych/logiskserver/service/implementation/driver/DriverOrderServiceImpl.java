package com.yanovych.logiskserver.service.implementation.driver;

import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.domain.OrderStatus;
import com.yanovych.logiskserver.domain.Transport;
import com.yanovych.logiskserver.dto.mapper.ResponseOrderDtoMapper;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.repository.OrderRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.driver.DriverOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ResponseOrderDto> getActiveOrders() {
        return null;
    }

    @Override
    public ResponseOrderDto get(Long id) {
        return null;
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
    public ResponseOrderDto updateOrderStatus(Long id, OrderStatus status) {
        return null;
    }
}
