package com.cda.locappartback.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ville", cascade = CascadeType.ALL)
    @JsonIgnore()
    private List<Appartement> appartements;

    public Ville(Long id) {
        this.id = id;
    }


}
