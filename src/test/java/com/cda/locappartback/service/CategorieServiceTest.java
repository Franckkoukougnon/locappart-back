package com.cda.locappartback.service;

import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategorieServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private CategorieService categorieService;

    @Test
    public void testSaveCategorie_ExistingCategorie(){

        //Given category
        Categorie existingCategorie = new Categorie();

        //When
        when(categoryRepo.findCategoryByName(anyString())).thenReturn(existingCategorie);

        //Then
        Categorie result = categorieService.saveCategorie(existingCategorie);

        //VerifyAndAssert
        assertEquals(existingCategorie, result);

        //Verify
        verify(categoryRepo, times(1)).findCategoryByName(anyString());

    }




    }



