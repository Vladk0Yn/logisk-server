package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
