package com.cda.locappartback.controller;

import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.entity.Ville;
import com.cda.locappartback.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/categories")
@CrossOrigin
public class CategoryController {


    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieService.getCategorieById(id);
        return categorie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PostMapping()
    public Categorie createCategorie(@RequestBody Categorie categorie){
        return categorieService.saveCategorie(categorie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        Optional<Categorie> existingCategorie = categorieService.getCategorieById(id);

        if (existingCategorie.isPresent()) {
            updatedCategorie.setId(id);
            Categorie savedCategorie = categorieService.saveCategorie(updatedCategorie);
            return new ResponseEntity<>(savedCategorie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        Optional<Categorie> existingCategorie = categorieService.getCategorieById(id);

        if (existingCategorie.isPresent()) {
            categorieService.deleteCategorie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
