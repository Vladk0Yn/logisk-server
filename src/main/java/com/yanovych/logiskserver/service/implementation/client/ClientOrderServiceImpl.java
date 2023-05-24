package com.yanovych.logiskserver.service.implementation.client;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.dto.mapper.RequestOrderDTOMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseOrderDTOMapper;
import com.yanovych.logiskserver.dto.request.RequestOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.repository.OrderRepository;
import com.yanovych.logiskserver.service.client.ClientOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderRepository orderRepository;

    @Override
    public ResponseOrderDto create(RequestOrderDto order) {
        Order queryOrder = RequestOrderDTOMapper.INSTANCE.dtoToOrder(order);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        queryOrder.setCreatedTime(timestamp);
        return ResponseOrderDTOMapper.INSTANCE.orderToDto(orderRepository.save(queryOrder));
    }

    @Override
    public List<ResponseOrderDto> getAll(Long id) {
        List<Order> orders = orderRepository.findOrdersByClient_Id(id);
        return orders.stream()
                .map(ResponseOrderDTOMapper.INSTANCE::orderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDto get(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return ResponseOrderDTOMapper.INSTANCE.orderToDto(order);
    }

    @Override
    public ResponseOrderDto update(RequestOrderDto order) {
        Order updatedOrder = RequestOrderDTOMapper.INSTANCE.dtoToOrder(order);
        return ResponseOrderDTOMapper.INSTANCE.orderToDto(orderRepository.save(updatedOrder));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
