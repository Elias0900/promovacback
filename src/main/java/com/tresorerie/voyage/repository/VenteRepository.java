package com.tresorerie.voyage.repository;

import com.tresorerie.voyage.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Integer> {

    @Query("SELECT v FROM Vente v WHERE v.user.id = :userId")
    List<Vente> findVentesByUserId(@Param("userId") Long userId);


    @Query("select sum(v.montantAssurance) from Vente v where v.user.id = :userId")
    Double totalMontantAssuranceByUserId(@Param("userId") Long userId);

    @Query("select sum(v.montantAssurance) from Vente v where v.user.id = :userId and v.tourOperateur like %:tourOperateur%")
    Double totalMontantAssuranceByUserIdForFramContaining(@Param("userId") Long userId, @Param("tourOperateur") String tourOperateur);


    @Query("select sum(v.montantAssurance) from Vente v where v.user.id = :userId and v.tourOperateur not like  %:tourOperateur%")
    Double TotalMontantAssuranceByUserIdForNonFram(@Param("userId") Long userId,  @Param("tourOperateur") String tourOperateur);

//    @Query("select v.dateValidation, sum(v.montantVenteTotale) from Vente v where v.user.id = :userId group by v.dateValidation order by v.dateValidation")
//    List<Object[]> findTotalVentesByDateForUser(@Param("userId") Long userId);


}
