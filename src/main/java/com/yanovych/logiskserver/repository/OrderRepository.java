package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByClient_Id(Long id);
}
