package com.tresorerie.voyage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private Role role; // Si le rôle est une énumération, vous pouvez le garder comme chaîne ou utiliser un type d'énumération approprié
    private List<SaleDTO> sales; // DTO pour la liste des ventes associées à l'utilisateur
}
