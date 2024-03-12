package com.cda.locappartback.service;


import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Bailleur;
import com.cda.locappartback.repository.BailleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BailleurService {


    @Autowired
    private BailleurRepository bailleurRepository;
    public List<Bailleur> getAllBailleur() {
        return bailleurRepository.findAll();
    }

    public Bailleur findById(Long id) {
        return bailleurRepository.findById(id).orElse(null);
    }

    public Bailleur save(Bailleur bailleur) {
        return bailleurRepository.save(bailleur);
    }

    public void deleteById(Long id) {
        bailleurRepository.deleteById(id);
    }

    

    public List<Appartement> findAppartementsByBailleurId(Long id) {
        Bailleur bailleur = bailleurRepository.findById(id).orElse(null);
        if (bailleur != null) {
            return bailleur.getAppartements();
        }
        return null;
    }
}
