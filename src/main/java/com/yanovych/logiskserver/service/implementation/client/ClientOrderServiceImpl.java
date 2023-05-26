package com.yanovych.logiskserver.service.implementation.client;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Location;
import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.mapper.RequestOrderDtoMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseOrderDtoMapper;
import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.repository.OrderRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.client.ClientOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {

    private final SessionUserService sessionUserService;
    private final OrderRepository orderRepository;

    private List<Order> getClientOrders() {
        return orderRepository.findOrdersByClient_Id(sessionUserService.getAuthenticatedUser().getId());
    }

    @Override
    public ResponseOrderDto create(RequestOrderDto order) {
        Order queryOrder = RequestOrderDtoMapper.INSTANCE.dtoToOrder(order);
        queryOrder.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        Client authenticatedClient = sessionUserService.getAuthenticatedUser().getClient();
        queryOrder.setClient(authenticatedClient);

        List<Long> queryLocations = List.of(
                queryOrder.getLocationTo().getId(),
                queryOrder.getLocationFrom().getId()
        );

        List<Long> clientLocations = authenticatedClient.getLocations()
                .stream()
                .map(Location::getId)
                .toList();

        boolean allLocationsExist = new HashSet<>(clientLocations).containsAll(queryLocations);

        if (allLocationsExist) {
            return ResponseOrderDtoMapper.INSTANCE.orderToDto(orderRepository.save(queryOrder));
        }

        return null;
    }

    @Override
    public List<ResponseOrderDto> getAll() {
        List<Order> orders = getClientOrders();
        return orders.stream()
                .map(ResponseOrderDtoMapper.INSTANCE::orderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDto get(Long id) {
        List<Order> orders = getClientOrders();
        return orders.stream()
                .filter(order -> Objects.equals(order.getId(), id))
                .map(ResponseOrderDtoMapper.INSTANCE::orderToDto)
                .findFirst().orElse(null);
    }

    @Override
    public ResponseOrderDto update(RequestOrderDto orderDto) {
        List<Order> orders = getClientOrders();
        Order updatedOrder = RequestOrderDtoMapper.INSTANCE.dtoToOrder(orderDto);
        if (orders.stream().anyMatch(order -> order.getId().equals(updatedOrder.getId()))) {
            return ResponseOrderDtoMapper.INSTANCE.orderToDto(orderRepository.save(updatedOrder));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        List<Order> orders = getClientOrders();
        if (orders.stream().anyMatch(order -> order.getId().equals(id))) {
            orderRepository.deleteById(id);
        }
    }
}
