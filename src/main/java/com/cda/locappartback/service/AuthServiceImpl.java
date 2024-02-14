package com.cda.locappartback.service;


import com.cda.locappartback.dto.SignupRequest;
import com.cda.locappartback.dto.UserDto;
import com.cda.locappartback.entity.User;
import com.cda.locappartback.enums.UserRole;
import com.cda.locappartback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AuthServiceImpl implements AuthService{



    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();





        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User createdCustomer  = userRepository.save(user);

        UserDto userCreateDto = new UserDto();

        userCreateDto.setId(createdCustomer.getId());
        userCreateDto.setEmail(createdCustomer.getEmail());
        userCreateDto.setName(createdCustomer.getName());
        userCreateDto.setPassword(createdCustomer.getPassword());
        userCreateDto.setUserRole(createdCustomer.getUserRole());



        return userCreateDto;
    }

    @Override
    public boolean customerWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
