package com.tresorerie.voyage.service;

import com.tresorerie.voyage.entity.Sale;
import com.tresorerie.voyage.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    public Sale save(Sale sale){
        return repository.saveAndFlush(sale);
    }

    public List<Sale> findAll(){
        return repository.findAll();
    }
}
