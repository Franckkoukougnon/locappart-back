package com.cda.locappartback.service;


import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.repository.AppartementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppartementService {
    @Autowired
    private AppartementRepo appartementRepository;

    @Autowired
    private CategorieService categorieService;

    public List<Appartement> getAllAppartements() {
        return appartementRepository.findAll();
    }

    public Optional<Appartement> getAppartementById(Long id) {
        return appartementRepository.findById(id);
    }

    public Appartement saveAppartement(Appartement appartement) {

        String categorieName = appartement.getCategorie().getName();

        Categorie categorie = categorieService.createOrGetCategorieByName(categorieName);

        appartement.setCategorie(categorie);

        return appartementRepository.save(appartement);
    }




    public void deleteAppartement(Long id) {
        appartementRepository.deleteById(id);
    }

    public List<Appartement> getAppartementsByCategories(List<Long> categories) {
        return appartementRepository.findByCategorieIdIn(categories);
    }
}
