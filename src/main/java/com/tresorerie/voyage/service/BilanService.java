package com.tresorerie.voyage.service;

import com.tresorerie.voyage.repository.BilanRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilanService {
    @Autowired
    private BilanRepository bilanRepository;


}
