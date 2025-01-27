package com.tresorerie.voyage.controller;

import com.tresorerie.voyage.config.ApiErrorResponse;
import com.tresorerie.voyage.config.ApiResponse;
import com.tresorerie.voyage.config.AuthResponse;
import com.tresorerie.voyage.jwt.JwtHelper;
import com.tresorerie.voyage.model.MyAppUser;
import com.tresorerie.voyage.repository.MyAppUserRepository;
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

    // Méthode d'inscription
    @PostMapping(value = "/req/signup", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody MyAppUser user) {
        try {
            // Vérifier si l'email est déjà utilisé
            if (myAppUserRepository.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "L'email est déjà utilisé."));
            }

            // Encoder le mot de passe
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Sauvegarder l'utilisateur
            MyAppUser createdUser = myAppUserRepository.save(user);

            // Sauvegarder le bilan de l'utilisateur
            bilanService.saveOrUpdateBilan(user.getId());

            // Générer le token JWT
            String token = JwtHelper.generateToken(createdUser.getEmail(), createdUser.getRoleType());

            // Répondre avec un message structuré et le token
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthResponse(true, "Utilisateur créé avec succès", token, createdUser.getRoleType(), createdUser.getEmail(), createdUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Erreur lors de la création de l'utilisateur : " + e.getMessage()));
        }
    }

    // Méthode de connexion
    @PostMapping(value = "/req/login", consumes = "application/json")
    public ResponseEntity<?> loginUser(@RequestBody MyAppUser user) {
        Optional<MyAppUser> existingUser = Optional.ofNullable(myAppUserRepository.findByEmail(user.getEmail()));

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Utilisateur non trouvé"));
        }

        // Vérification du mot de passe
        if (passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            // Générer un token JWT
            String token = JwtHelper.generateToken(existingUser.get().getEmail(), existingUser.get().getRoleType());

            // Répondre avec le token et les détails de l'utilisateur
            return ResponseEntity.ok().body(new AuthResponse(true, "Connexion réussie", token,existingUser.get().getRoleType(), existingUser.get().getEmail() , existingUser.get().getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Identifiant ou mot de passe incorrect"));
        }
    }
}
