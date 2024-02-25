package com.cda.locappartback.controller;


import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Bailleur;
import com.cda.locappartback.service.BailleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/bailleur")
@CrossOrigin
public class BailleurControlleur {

    @Autowired
    private BailleurService bailleurService;

    @GetMapping
    public ResponseEntity<List<Bailleur>> getAllBailleur() {
        List<Bailleur> bailleurs = bailleurService.getAllBailleur();
        return new ResponseEntity<>(bailleurs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Bailleur getBailleurById(@PathVariable Long id) {
        return bailleurService.findById(id);
    }

    @PostMapping
    public Bailleur createBailleur(@RequestBody Bailleur bailleur) {
        return bailleurService.save(bailleur);
    }

    @PutMapping("/{id}")
    public Bailleur updateBailleur(@PathVariable Long id, @RequestBody Bailleur bailleur) {
        bailleur.setId(id);
        return bailleurService.save(bailleur);
    }

    @DeleteMapping("/{id}")
    public void deleteBailleur(@PathVariable Long id) {
        bailleurService.deleteById(id);
    }

    // Nouvelle route pour afficher les appartements d'un bailleur
    @GetMapping("/{id}/appartements")
    public List<Appartement> getAppartementsByBailleur(@PathVariable Long id) {
        return bailleurService.findAppartementsByBailleurId(id);
    }
}
