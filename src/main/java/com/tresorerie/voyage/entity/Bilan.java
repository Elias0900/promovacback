package com.tresorerie.voyage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bilan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double objectif;
    private double realise;
    private double pourcentageRealise;
    private double pourcentageFram;
    private double pourcentageAssurance;
    private double framCroisieres;
    private double autresTO; // Autres Tour Opérateurs
    private double assurances;
    private double totalPrimesBrutes;
}
