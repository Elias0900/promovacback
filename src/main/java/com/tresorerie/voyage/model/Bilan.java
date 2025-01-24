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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    private double objectif; // Objectif
    private double realise; // Réalisé
    private double pourcentageRealise; // % Réalisé
    private double pourcentageFram; // % FRAM
    private double pourcentageAssurance; // % ASSURANCE

    private double framCroisieres; // FRAM - CROISIERES
    private double autresTo; // AUTRES TO
    private double assurances; // ASSURANCES
    private double totalPrimesBrutes; // TOTAL PRIMES BRUTES
    private double totalPrimesAutre; // TOTAL PRIMES Autre
    private double totalPrimesFram; // TOTAL PRIMES FRAM
    private double totalPrimesAss; // TOTAL PRIMES FRAM



    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // Clé étrangère vers User
    private MyAppUser user; // Relation avec l'utilisateur

    public void setUser(MyAppUser user) {
        this.user = user;
        if (user != null) {
            user.setBilan(this);  // Assurez-vous que l'utilisateur référence correctement le bilan
        }
    }
}
