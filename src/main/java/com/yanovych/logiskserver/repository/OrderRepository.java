package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Order;
import com.yanovych.logiskserver.domain.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByClient_IdOrderByCreatedTimeDesc(Long id);
    List<Order> findOrdersByDriver_IdOrderByDeliverDueTimeDesc(Long id);
    List<Order> findOrdersByStatusOrderByCreatedTimeDesc(OrderStatus orderStatus);
}
