package com.cda.locappartback.controller;

import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Ville;
import com.cda.locappartback.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ville")
@CrossOrigin
public class VilleController {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getAllVille() {
        return villeService.getAllVille();
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
        return villeService.findVilleById(id);
    }

    @PostMapping()
    public Ville createVille(@RequestBody Ville ville) {
        return villeService.saveVille(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Long id, @RequestBody Ville ville) {
        ville.setId(id);
        return villeService.saveVille(ville);
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Long id) {
        villeService.deleteVilleById(id);
    }

    @GetMapping("/{id}/appartements")
    public List<Appartement> getAppartementsByVille(@PathVariable Long id) {
        return villeService.findAppartementsByVilleId(id);
    }





}
