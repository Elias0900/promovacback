package com.tresorerie.voyage.service;

import com.tresorerie.voyage.entity.Sale;
import com.tresorerie.voyage.entity.User;
import com.tresorerie.voyage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Sale> findSalesByUser(String username){
        return userRepository.findSalesByUsername(username);
    }
}
