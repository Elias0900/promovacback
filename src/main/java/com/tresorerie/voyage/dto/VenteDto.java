package com.tresorerie.voyage.dto;

import com.tresorerie.voyage.model.Vente;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenteDto {

    private Long id; // Identifiant de la vente
    private String nom; // Nom de l'utilisateur
    private String prenom; // Prénom de l'utilisateur
    private String numeroDossier; // Numéro de dossier
    private int pax; // Nombre de personnes
    private LocalDate dateValidation; // Date de validation
    private LocalDate dateDepart; // Date de départ
    private String tourOperateur; // Tour opérateur (TO)
    private BigDecimal venteTotal; // Montant de la vente totale
    private boolean assurance; // Indique si une assurance a été souscrite
    private double montantAssurance; // Montant de l'assurance
    private double fraisAgence; // Frais d'agence

    /**
     * Convertit une entité `Vente` en un DTO `VenteDto`.
     *
     * @param vente L'entité source.
     * @return Un objet `VenteDto`.
     */
    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) {
            return null;
        }

        return VenteDto.builder()
                .id(vente.getId())
                .nom(vente.getNom())
                .prenom(vente.getPrenom())
                .numeroDossier(vente.getNumeroDossier())
                .pax(vente.getPax())
                .dateValidation(vente.getDateValidation())
                .dateDepart(vente.getDateDepart())
                .tourOperateur(vente.getTourOperateur())
                .venteTotal(vente.getVenteTotal())
                .assurance(vente.isAssurance())
                .montantAssurance(vente.getMontantAssurance())
                .fraisAgence(vente.getFraisAgence())
                .build();
    }

    /**
     * Convertit un DTO `VenteDto` en une entité `Vente`.
     *
     * @param venteDto Le DTO source.
     * @return Une entité `Vente`.
     */
    public static Vente toEntity(VenteDto venteDto) {
        if (venteDto == null) {
            return null;
        }

        return Vente.builder()
                .id(venteDto.getId())
                .nom(venteDto.getNom())
                .prenom(venteDto.getPrenom())
                .numeroDossier(venteDto.getNumeroDossier())
                .pax(venteDto.getPax())
                .dateValidation(venteDto.getDateValidation())
                .dateDepart(venteDto.getDateDepart())
                .tourOperateur(venteDto.getTourOperateur())
                .venteTotal(venteDto.getVenteTotal())
                .assurance(venteDto.isAssurance())
                .montantAssurance(venteDto.getMontantAssurance())
                .fraisAgence(venteDto.getFraisAgence())
                .build();
    }
}
