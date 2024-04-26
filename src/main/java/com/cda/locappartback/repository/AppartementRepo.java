package com.cda.locappartback.repository;

import com.cda.locappartback.entity.Appartement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppartementRepo extends JpaRepository<Appartement, Long>{

    List<Appartement> findByCategorieIdIn(List<Long> categories);

    List<Appartement> findByVilleId(Long id);
}
