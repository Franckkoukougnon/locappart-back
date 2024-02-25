package com.cda.locappartback.controller;


import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.entity.Role;
import com.cda.locappartback.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role roleCreated = roleService.saveRole(role);
        return new ResponseEntity<>( roleCreated, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleService.getAllRoles();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }
}
