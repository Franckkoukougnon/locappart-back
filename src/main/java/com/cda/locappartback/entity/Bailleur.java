package com.cda.locappartback.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Bailleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bailleur", cascade = CascadeType.ALL)
    @JsonIgnore()
    private List<Appartement> appartements;



}
