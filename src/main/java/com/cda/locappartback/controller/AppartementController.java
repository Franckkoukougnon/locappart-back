package com.cda.locappartback.controller;

import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Bailleur;
import com.cda.locappartback.service.AppartementService;
import com.cda.locappartback.service.BailleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/appart")
@CrossOrigin
public class AppartementController {


    @Autowired
    private AppartementService appartementService;

    @Autowired
    private BailleurService bailleurService;

    @GetMapping
    public ResponseEntity<List<Appartement>> getAllAppartements() {
        List<Appartement> appartements = appartementService.getAllAppartements();
        return new ResponseEntity<>(appartements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appartement> getAppartementById(@PathVariable Long id) {
        Optional<Appartement> appartement = appartementService.getAppartementById(id);
        return appartement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Appartement> createAppartement(@RequestBody Appartement appartement) {
        // Récupérer l'ID du bailleur sélectionné à partir de la requête
        Long bailleurId = appartement.getBailleur().getId();


        // Récupérer le bailleur à partir de la base de données
        Bailleur bailleur = bailleurService.findById(bailleurId);

        // Valider l'existence du bailleur
        if (bailleur == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        appartement.setBailleur(bailleur);

        Appartement savedAppartement = appartementService.saveAppartement(appartement);
        return new ResponseEntity<>(savedAppartement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appartement> updateAppartement(@PathVariable Long id, @RequestBody Appartement updatedAppartement) {
        Optional<Appartement> existingAppartement = appartementService.getAppartementById(id);

        if (existingAppartement.isPresent()) {
            updatedAppartement.setId(id);
            Appartement savedAppartement = appartementService.saveAppartement(updatedAppartement);
            return new ResponseEntity<>(savedAppartement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppartement(@PathVariable Long id) {
        Optional<Appartement> existingAppartement = appartementService.getAppartementById(id);

        if (existingAppartement.isPresent()) {
            appartementService.deleteAppartement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
