package com.tresorerie.voyage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Bilan  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String objectif; // Objectif
    private double realise; // Réalisé
    private double pourcentageRealise; // % Réalisé
    private double pourcentageFram; // % FRAM
    private double pourcentageAssurance; // % ASSURANCE

    private double framCroisieres; // FRAM - CROISIERES
    private double autresTo; // AUTRES TO
    private double assurances; // ASSURANCES
    private double totalPrimesBrutes; // TOTAL PRIMES BRUTES



    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // Clé étrangère vers User
    private MyAppUser user; // Relation avec l'utilisateur
}
