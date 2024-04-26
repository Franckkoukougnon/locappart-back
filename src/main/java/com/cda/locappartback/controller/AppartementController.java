package com.cda.locappartback.controller;

import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Bailleur;
import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.entity.Ville;
import com.cda.locappartback.service.AppartementService;
import com.cda.locappartback.service.BailleurService;
import com.cda.locappartback.service.CategorieService;
import com.cda.locappartback.service.VilleService;
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

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private VilleService villeService;




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

    @GetMapping("/appartements")
    public List<Appartement> getAppartementsByCategories(@RequestParam List<Long> categories) {
        return appartementService.getAppartementsByCategories(categories);
    }



    @PostMapping
    public Appartement addAppartement(@RequestBody Appartement appartement){

        // je recupere les Id
        long categoryId = appartement.getCategorie().getId();
        long bailleurId = appartement.getBailleur().getId();
        long villeId = appartement.getVille().getId();

        // je verifie si les categorie et ls bailleur existent
        Optional<Categorie> category = categorieService.getCategorieById(categoryId);
        Bailleur bailleur = bailleurService.findById(bailleurId);
        Ville ville = villeService.findVilleById(villeId);



        if(category.isEmpty()) {
            throw new IllegalArgumentException("La catégorie spécifiée n'existe pas.");
        }

        if(bailleur == null){
            throw new IllegalArgumentException("Le bailleur spécifié n'existe pas.");
        }

        if(ville == null){
            throw new IllegalArgumentException("La ville spécifiée n'existe pas.");
        }

        // Ajouter le bailleur et la catégorie à l'appartement
        appartement.setCategorie(category.get());
        appartement.setBailleur(bailleur);
        appartement.setVille(ville);

        return appartementService.saveAppartement(appartement);

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
