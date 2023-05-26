package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Location;
import com.yanovych.logiskserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findLocationsByClient_Id(Long id);
}
