package com.tresorerie.voyage.controller;

import com.tresorerie.voyage.model.Bilan;
import com.tresorerie.voyage.model.MyAppUser;
import com.tresorerie.voyage.repository.MyAppUserRepository;
import com.tresorerie.voyage.service.MyAppUserServiceImpl;
import com.tresorerie.voyage.service.interf.BilanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BilanService bilanService;

    @PostMapping(value = "/req/signup", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody MyAppUser user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            MyAppUser createdUser = myAppUserRepository.save(user);
            bilanService.saveOrUpdateBilan(user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création de l'utilisateur : " + e.getMessage());
        }
    }


    @PostMapping(value = "/req/login", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody MyAppUser user) {
        System.out.println("Login endpoint hit with username: " + user.getUsername());

        // Recherche de l'utilisateur par son nom d'utilisateur (ou email)
        Optional<MyAppUser> existingUser = myAppUserRepository.findByUsername(user.getUsername());

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        // Vérification du mot de passe
        if (passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword()    )) {
            return ResponseEntity.ok("Connexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }
    }


}