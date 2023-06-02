package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Long> {
}
