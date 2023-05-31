package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByClient_IdOrderByCreatedTimeDesc(Long id);

    List<Order> findOrdersByStatusAndWidthLessThanAndWeightLessThanAndHeightLessThanOrderByCreatedTimeDesc(OrderStatus orderStatus, Double width, Double weight, Double height);
}
