package com.tresorerie.voyage.repository;

import com.tresorerie.voyage.model.Bilan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BilanRepository extends JpaRepository<Bilan, Long> {
    Optional<Bilan> findByUserId(Long userId);

}
