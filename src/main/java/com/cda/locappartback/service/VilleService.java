package com.cda.locappartback.service;


import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Ville;
import com.cda.locappartback.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;


    public List<Ville> getAllVille() {
        return villeRepository.findAll();
    }

    public Ville findVilleById(Long id) {
        return villeRepository.findById(id).orElse(null);
    }

    public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
    }

    public void deleteVilleById(Long id) {
        villeRepository.deleteById(id);
    }


    public List<Appartement> findAppartementsByVilleId(Long id) {
        Ville ville = villeRepository.findById(id).orElse(null);
        if (ville != null) {
            return ville.getAppartements();
        }
        return null;
    }
}
