package com.cda.locappartback.service;


import com.cda.locappartback.dto.SignupRequest;
import com.cda.locappartback.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean customerWithEmail(String email);


}
