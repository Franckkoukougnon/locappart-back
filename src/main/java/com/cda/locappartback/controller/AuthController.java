package com.cda.locappartback.controller;


import com.cda.locappartback.dto.SignupRequest;
import com.cda.locappartback.dto.UserDto;
import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private  AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest) {

        if(authService.customerWithEmail(signupRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");

        }



      UserDto createCustomerDto = authService.createCustomer(signupRequest);
      if(createCustomerDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
      }
        return ResponseEntity.status(HttpStatus.CREATED).body(createCustomerDto);

    }








}
