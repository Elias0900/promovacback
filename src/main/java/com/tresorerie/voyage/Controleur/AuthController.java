package com.tresorerie.voyage.Controleur;

import com.tresorerie.voyage.Auth.AuthSystem;
import com.tresorerie.voyage.DTO.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthSystem authSystem;

    // Endpoint pour créer un utilisateur avec des paramètres dans le body
    @PostMapping("/users")
    public ResponseEntity<String> creerUtilisateur(@RequestBody UserRequest userRequest) {
        try {
            authSystem.creerUtilisateur(userRequest.getIdentifiant(), userRequest.getMotDePasse(), userRequest.getRole());
            return ResponseEntity.ok("Utilisateur créé avec succès");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Erreur lors de la création de l'utilisateur");
        }
    }

    // Endpoint pour l'authentification de l'utilisateur
    @PostMapping("/login")
    public ResponseEntity<String> authentifier(@RequestBody UserRequest userRequest) {
        try {
            if (authSystem.authentifier(userRequest.getIdentifiant(), userRequest.getMotDePasse())) {
                return ResponseEntity.ok("Authentification réussie");
            } else {
                return ResponseEntity.status(401).body("Identifiant ou mot de passe incorrect");
            }
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Erreur lors de l'authentification");
        }
    }
}
