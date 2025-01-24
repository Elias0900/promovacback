package com.tresorerie.voyage.repository;

import java.util.Optional;

import com.tresorerie.voyage.model.MyAppUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long>{

    Optional<MyAppUser> findByUsername(String username);

    MyAppUser findByEmail( String email);
}