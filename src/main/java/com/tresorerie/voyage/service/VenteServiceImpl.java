package com.tresorerie.voyage.service;

import com.tresorerie.voyage.dto.VenteDto;
import com.tresorerie.voyage.dto.VentesParJourDto;
import com.tresorerie.voyage.model.MyAppUser;
import com.tresorerie.voyage.model.Vente;
import com.tresorerie.voyage.repository.MyAppUserRepository;
import com.tresorerie.voyage.repository.VenteRepository;
import com.tresorerie.voyage.service.interf.BilanService;
import com.tresorerie.voyage.service.interf.VenteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private BilanService bilanService;

    @Override
    public VenteDto saveOrUpdateVente(VenteDto venteDto) {
        // Charger l'utilisateur depuis la base de données
        MyAppUser user = myAppUserRepository.findById(venteDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + venteDto.getUserId()));

        // Convertir le DTO en entité
        Vente vente = VenteDto.toEntity(venteDto);
        vente.setUser(user); // Associer l'utilisateur chargé
        vente.setTotalSansAssurance(vente.getVenteTotal().doubleValue() - vente.getMontantAssurance());

        // Sauvegarder la vente
        Vente savedVente = venteRepository.save(vente);

        bilanService.saveOrUpdateBilan(user.getId());
        // Retourner le DTO
        return VenteDto.fromEntity(savedVente);
    }


    @Override
    public List<VenteDto> findVentesByUserId(Long userId) {
        List<Vente> ventes = venteRepository.findVentesByUserId(userId); // Récupération des ventes
        return ventes.stream()
                .map(VenteDto::fromEntity) // Conversion Entités -> DTOs
                .toList();
    }

    @Override
    public Double totalMontantAssuranceByUserId(Long userId) {
        return venteRepository.totalMontantAssuranceByUserId(userId);
    }

    @Override
    public Double totalMontantTOByUserIdForFramContaining(Long userId, String tourOperateur) {
        return venteRepository.totalMontantTOByUserIdForFramContaining(userId, "%" + "FRAM" + "%");

    }

    @Override
    public Double totalMontantTOByUserIdForNonFram(Long userId, String tourOperateur) {
        return venteRepository.totalMontantTOByUserIdForNonFram(userId, "%" + "FRAM" + "%");

    }

    @Override
    public List<VentesParJourDto> venteParJour(LocalDateTime startDate, LocalDateTime endDate) {
        // Convertir LocalDateTime en LocalDate pour que le type corresponde avec la base de données
        LocalDate start = startDate.toLocalDate(); // Juste la date sans l'heure
        LocalDate end = endDate.toLocalDate(); // Juste la date sans l'heure

        // Appel de la méthode du repository avec les dates ajustées
        return venteRepository.findVentesParJour(start, end);
    }
    @Override
    public List<VentesParJourDto> venteParJourByUser(LocalDateTime startDate, LocalDateTime endDate, Long userId) {
        // Convertir LocalDateTime en LocalDate pour que le type corresponde avec la base de données
        LocalDate start = startDate.toLocalDate(); // Juste la date sans l'heure
        LocalDate end = endDate.toLocalDate(); // Juste la date sans l'heure

        // Appel de la méthode du repository avec les dates ajustées
        return venteRepository.findVentesParJourByUser(start, end, userId);
    }


}
