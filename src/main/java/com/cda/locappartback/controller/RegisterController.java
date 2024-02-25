package com.cda.locappartback.controller;

import com.cda.locappartback.dto.AuthResponseDTO;
import com.cda.locappartback.dto.RegisterDto;
import com.cda.locappartback.dto.UserDto;
import com.cda.locappartback.entity.User;
import com.cda.locappartback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        userService.registerNewUserAccount(registerDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/listuser")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());

    }





}