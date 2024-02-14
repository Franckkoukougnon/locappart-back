package com.cda.locappartback.service;


import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategorieService {
    @Autowired
    private CategoryRepo categorieRepository;

    private Set<Categorie> categories= new HashSet<>();

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategorie(Categorie categorie) {
        String categorieName = categorie.getName();
        Categorie existingCategorie = categorieRepository.findCategoryByName(categorieName);

        if(existingCategorie != null){
            return existingCategorie;
        } else {
            return categorieRepository.save(categorie);
        }
    }

    public Categorie createOrGetCategorieByName(Long CategoryId) {
        Categorie existingCategorie = categorieRepository.findById(CategoryId).orElse(null);
        if (existingCategorie != null) {
            return existingCategorie;
        }
        return existingCategorie;
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
