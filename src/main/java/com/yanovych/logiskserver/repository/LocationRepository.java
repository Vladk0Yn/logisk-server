package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
