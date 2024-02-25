package com.cda.locappartback.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.ALL)
    @JsonIgnore()
    private List<User> users;

    public Role(String user) {
    }
}
