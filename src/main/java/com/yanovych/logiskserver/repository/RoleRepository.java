package com.yanovych.logiskserver.repository;

import com.yanovych.logiskserver.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
