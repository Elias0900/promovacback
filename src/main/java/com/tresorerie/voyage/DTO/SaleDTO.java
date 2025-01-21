package com.tresorerie.voyage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    private long id;
    private String nom;
    private String prenom;
    private String numeroDossier;
    private int pax; // Nombre de personnes
    private Date dateValidation;
    private Date dateDepart;
    private String tourOperateur; // Tour opérateur
    private double montantVenteTotale;
    private boolean assurance; // True si assurance est prise
    private double montantAssurance;
    private double fraisAgence;
}
