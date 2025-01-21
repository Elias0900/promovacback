package com.tresorerie.voyage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "user_id") // Colonne pour la clé étrangère
    private User user; // Référence à l'utilisateur
}
