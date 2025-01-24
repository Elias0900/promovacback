package com.tresorerie.voyage.dto;

import java.util.List;

public class MyAppUserDto {
    private Long id;
    private String username;
    private String email;
    private String roleName; // Nom du rôle
    private List<Long> transactionIds; // Liste des IDs des transactions
    private Long bilanId; // ID du bilan
}
