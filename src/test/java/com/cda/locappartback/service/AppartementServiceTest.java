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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppartementServiceTest {

        @Mock
        private AppartementRepo appartementRepo;

        @InjectMocks
        private AppartementService appartementService;

        @Test
        void getAllAppartements() {
            appartementService.getAllAppartements();
            verify(appartementRepo).findAll();
        }

        @Test
        void getAppartementById() {
            Appartement appartement = new Appartement();
            appartement.setId(1L);
            when(appartementRepo.findById(1L)).thenReturn(java.util.Optional.of(appartement));
            appartementService.getAppartementById(1L);
            verify(appartementRepo).findById(1L);
        }
}
