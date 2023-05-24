package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
