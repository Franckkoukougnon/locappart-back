package com.cda.locappartback.service;


import com.cda.locappartback.entity.Appartement;
import com.cda.locappartback.entity.Whistlist;
import com.cda.locappartback.repository.AppartementRepo;
import com.cda.locappartback.repository.WhistlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WhistlistService {

    @Autowired
    private WhistlistRepository whistlistRepository;

    @Autowired
    private AppartementRepo appartementRepo;

    public List<Whistlist> getAllWhistlists() {
        return whistlistRepository.findAll();
    }

    public Optional<Whistlist> getWhistlistById(Long id) {
        return whistlistRepository.findById(id);
    }

    public Whistlist createWhistlist(Whistlist whistlist) {
        return whistlistRepository.save(whistlist);
    }

    public void deleteWhistlist(Long id) {
        whistlistRepository.deleteById(id);
    }




    public Whistlist addAppartementToWhistlist(Long whistlistId, Long appartementId) {
        // Récupérer la wishlist
        Whistlist whistlist = whistlistRepository.findById(whistlistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wishlist not found with id: " + whistlistId));

        // Récupérer l'appartement
        Appartement appartement = appartementRepo.findById(appartementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appartement not found with id: " + appartementId));

        // Ajouter l'appartement à la wishlist
        whistlist.getAppartements().add(appartement);

        // Mettre à jour la wishlist dans la base de données
        return whistlistRepository.save(whistlist);
    }

    public Whistlist removeAppartementFromWhistlist(Long whistlistId, Long appartementId) {
        // Récupérer la wishlist
        Whistlist whistlist = whistlistRepository.findById(whistlistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wishlist not found with id: " + whistlistId));

        // Récupérer l'appartement
        Appartement appartement = appartementRepo.findById(appartementId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appartement not found"));


        // Supprimer l'appartement de la wishlist
        whistlist.getAppartements().remove(appartement);

        //Mise a jour de la whistlist
        return  whistlistRepository.save(whistlist);




    }
}
