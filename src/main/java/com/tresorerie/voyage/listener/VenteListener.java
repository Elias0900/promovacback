package com.tresorerie.voyage.listener;

import com.tresorerie.voyage.model.Vente;
import com.tresorerie.voyage.service.MyAppUserService;
import com.tresorerie.voyage.service.interf.BilanService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class VenteListener {

    private final BilanService bilanService;


    @Autowired
    public VenteListener(@Lazy BilanService bilanService) {
        this.bilanService = bilanService;
    }
    @PostPersist
    public void postPersist(Vente vente) {

        // Met à jour le bilan uniquement si l'utilisateur est défini
        if (vente.getUser().getId() != null) {
            Long userId = vente.getUser().getId();
            bilanService.saveOrUpdateBilan(userId);
        }
    }

    @PostUpdate
    public void postUpdate(Vente vente) {
        // Met à jour le bilan uniquement si l'utilisateur est défini
        if (vente.getUser() != null) {
            bilanService.saveOrUpdateBilan(vente.getUser().getId());
        }
    }
}
