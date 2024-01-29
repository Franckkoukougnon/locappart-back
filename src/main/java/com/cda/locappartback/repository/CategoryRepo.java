package com.cda.locappartback.repository;

import com.cda.locappartback.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Categorie, Long> {
    Categorie findCategoryByName(String name);
}
