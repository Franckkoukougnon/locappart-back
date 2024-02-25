package com.cda.locappartback.service;


import com.cda.locappartback.entity.Role;
import com.cda.locappartback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;


    public Role saveRole(Role role) {
        String roleName = role.getName();
        Role existingRole = roleRepository.findRoleByName(roleName);

        if (existingRole != null) {
            return existingRole;
        } else {
            return roleRepository.save(role);
        }

    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
