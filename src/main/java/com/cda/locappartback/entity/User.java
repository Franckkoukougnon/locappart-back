package com.cda.locappartback.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private LocalDateTime tokenExpiration;

    private String claimToken;

}
