package com.cda.locappartback.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Appartement  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private double currentPrice;
    private String description;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private int nbSallesDeBain;
    private int nbChambres;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categorie_id", nullable = true)
    @JsonIgnoreProperties("appartements")
    private Categorie categorie;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bailleur_id", nullable = true)
    @JsonIgnoreProperties("appartements")
    private Bailleur bailleur;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "whistlist_id", nullable = true)
    @JsonIgnoreProperties("appartements")
    private Whistlist whistlist;



}
