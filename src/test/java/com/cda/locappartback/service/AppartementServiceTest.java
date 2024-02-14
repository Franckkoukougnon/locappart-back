package com.cda.locappartback.service;

import com.cda.locappartback.controller.AppartementController;
import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.repository.AppartementRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppartementServiceTest {

    @Mock
    AppartementRepo appartementRepository;
    @Mock
    CategorieService categorieService;

    @InjectMocks
    AppartementService appartementService  = new AppartementService() ;


    @Test
    void saveAppartement() {
        //Given , je cree les variable que je vais utiliser
        Appartement appartementTest = new Appartement();
        Categorie appartementCategorie = new Categorie();
        appartementCategorie.setName("test");
        appartementTest.setCategorie(appartementCategorie);


        Appartement appartementResult = new Appartement();
        appartementResult.setId(1L);
        appartementResult.setCategorie(appartementCategorie);

        when(categorieService.createOrGetCategorieByName(1L)).thenReturn(appartementCategorie);
        when(appartementRepository.save(appartementTest)).thenReturn(appartementResult);

        //When
        Appartement appartementSaved = appartementService.saveAppartement(appartementTest);
        //Then
        assertEquals(appartementResult,appartementSaved);










    }
}