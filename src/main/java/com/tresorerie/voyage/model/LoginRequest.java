package com.tresorerie.voyage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
// Classe interne pour la demande de connexion
public class LoginRequest {
    private String username;
    private String password;


}