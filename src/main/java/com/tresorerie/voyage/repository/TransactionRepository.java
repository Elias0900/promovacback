package com.tresorerie.voyage.repository;


import com.tresorerie.voyage.model.Bilan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Bilan, Integer> {

    Bilan  findByUserId(Integer userId);
    
}

