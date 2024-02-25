package com.cda.locappartback.repository;

import com.cda.locappartback.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByName(String roleName);

    Optional<Role> findByName(String user);
}
