package com.tresorerie.voyage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tresorerie.voyage.listener.VenteListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
@EntityListeners(VenteListener.class) // Attacher le listener à l'entité
public class Vente  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    private BigDecimal venteTotal; // Montant total de la vente

    private String nom; // Nom de l'utilisateur
    private String prenom; // Prénom de l'utilisateur
    private String numeroDossier; // Numéro de dossier
    private int pax; // Nombre de personnes
    private LocalDate dateValidation; // Date de validation
    private LocalDate dateDepart; // Date de départ
    private String tourOperateur; // Tour opérateur (TO)
    private double montantAssurance; // Montant de l'assurance
    private double fraisAgence; // Frais d'agence
    private double totalSansAssurance;

    private boolean assurance; // Indique si une assurance a été souscrite

    @Column(updatable = false)
    private LocalDateTime transactionDate; // Date et heure de la transaction

    @ManyToOne
    @JoinColumn(name = "id_user")
    private MyAppUser user;

}
