package com.cda.locappartback.controller;


import com.cda.locappartback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favoris")
public class FavorisController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/{idAppartement}")
    public void addFavoris(@PathVariable Long userId, @PathVariable Long idAppartement) {
        userService.addFavoris(userId, idAppartement);
    }

    @DeleteMapping("/{userId}/{idAppartement}")
    public void deleteFavoris(@PathVariable Long userId, @PathVariable Long idAppartement) {
        userService.deleteFavoris(userId, idAppartement);
    }









}
