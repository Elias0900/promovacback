package com.tresorerie.voyage.dto;

import com.tresorerie.voyage.model.Bilan;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BilanDto {

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
    private Long userId; // Identifiant de l'utilisateur associé

    public static BilanDto fromEntity(Bilan bilan) {
        if (bilan == null) {
            return null;
        }

        return BilanDto.builder()
                .id(bilan.getId())
                .objectif(bilan.getObjectif())
                .realise(bilan.getRealise())
                .pourcentageRealise(bilan.getPourcentageRealise())
                .pourcentageFram(bilan.getPourcentageFram())
                .pourcentageAssurance(bilan.getPourcentageAssurance())
                .framCroisieres(bilan.getFramCroisieres())
                .autresTo(bilan.getAutresTo())
                .assurances(bilan.getAssurances())
                .totalPrimesBrutes(bilan.getTotalPrimesBrutes())
                .userId(bilan.getUser() != null ? bilan.getUser().getId() : null)
                .build();
    }

    public static Bilan toEntity(BilanDto bilanDto) {
        if (bilanDto == null) {
            return null;
        }

        return Bilan.builder()
                .id(bilanDto.getId())
                .objectif(bilanDto.getObjectif())
                .realise(bilanDto.getRealise())
                .pourcentageRealise(bilanDto.getPourcentageRealise())
                .pourcentageFram(bilanDto.getPourcentageFram())
                .pourcentageAssurance(bilanDto.getPourcentageAssurance())
                .framCroisieres(bilanDto.getFramCroisieres())
                .autresTo(bilanDto.getAutresTo())
                .assurances(bilanDto.getAssurances())
                .totalPrimesBrutes(bilanDto.getTotalPrimesBrutes())
                .build();
    }
}
