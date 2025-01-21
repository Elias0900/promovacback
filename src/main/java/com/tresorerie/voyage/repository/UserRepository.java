package com.tresorerie.voyage.repository;

import com.tresorerie.voyage.entity.Sale;
import com.tresorerie.voyage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<Sale>findSalesByUsername(String username);
}
