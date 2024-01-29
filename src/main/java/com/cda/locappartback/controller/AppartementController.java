package com.cda.locappartback.controller;

import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/appart")
public class AppartementController {


    @Autowired
    private AppartementService appartementService;

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
