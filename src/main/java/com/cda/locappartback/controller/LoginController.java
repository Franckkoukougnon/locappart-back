package com.cda.locappartback.controller;


import com.cda.locappartback.config.JwtTokenProvider;
import com.cda.locappartback.dto.AuthResponseDTO;
import com.cda.locappartback.dto.LoginDTO;
import com.cda.locappartback.entity.User;
import com.cda.locappartback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            //Authentication authentication_info = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(authentication);


            // Gather the infos on user
            Long userId = userService.getUserIdFromAuthentication(authentication);
            String userName = loginDto.getUsername();
            User user = userService.getUser(loginDto.getUsername());

            List<String> roles = userService.getUserRoles(user);

            // Create security token for user
            String token = jwtTokenProvider.generateToken( userName, roles, userId );

            return ResponseEntity.ok(new AuthResponseDTO(token, userName, userId));
        } catch (AuthenticationException e) {


            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

}
