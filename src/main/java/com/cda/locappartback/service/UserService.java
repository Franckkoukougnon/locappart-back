package com.cda.locappartback.service;


import com.cda.locappartback.dto.AuthResponseDTO;
import com.cda.locappartback.dto.RegisterDto;
import com.cda.locappartback.entity.Role;
import com.cda.locappartback.entity.User;
import com.cda.locappartback.exception.EmailAlreadyExistsException;
import com.cda.locappartback.repository.AppartementRepo;
import com.cda.locappartback.repository.RoleRepository;
import com.cda.locappartback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppartementRepo appartementRepo;


    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerNewUserAccount(RegisterDto registerDto) {

        //Je verifie si l'email est deja utilisé
        User existingUserByEmail = userRepository.findByEmail(registerDto.getEmail());
        if (existingUserByEmail != null) {
            throw new EmailAlreadyExistsException("L'email est déjà utilisé par un autre utilisateur.");
        }


        // Je crée un nouvel utilisateur
        User newUser = new User();

        newUser.setId(registerDto.getId());
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        newUser.setEmail(registerDto.getEmail());
        newUser.setTokenExpiration(LocalDateTime.now().plusDays(1));
        newUser.setEnabled(true); // Par défaut, l'utilisateur est activé

        // Récupération le rôle par défaut (s'il existe en base de données)
        Role defaultRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Default role not found."));

        // Attribution du rôle par défaut à l'utilisateur
        newUser.setRoles(Collections.singletonList(defaultRole));


        return userRepository.save(newUser);

    }

    public User getUserByToken(String token) {
        User user = userRepository.findByClaimToken(token);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new UsernameNotFoundException("Token expired");
        } else if (user.isEnabled()) {
            throw new UsernameNotFoundException("User already enabled");
        }

        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<String> getUserRoles(String username) {
        User user = getUser(username);
        return getUserRoles(user);
    }


    public List<String> getUserRoles(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public AuthResponseDTO createUser(RegisterDto registerDto) {
        return null;

    }


    public Long findUserIdByUsername(String username) {
        return userRepository.findUserIdByUsername(username);
    }

    public Long getUserIdFromAuthentication(Authentication authentication) {
        // Vérifiez d'abord si l'objet Authentication n'est pas null
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            // Obtenez les détails de l'utilisateur à partir de l'objet Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();


            // Extrayez l'identifiant de l'utilisateur à partir des détails de l'utilisateur
            return userRepository.findUserIdByUsername(userDetails.getUsername());
        }

        // Si l'objet Authentication est null ou ne contient pas de UserDetails, retournez null
        return null;
    }

}


