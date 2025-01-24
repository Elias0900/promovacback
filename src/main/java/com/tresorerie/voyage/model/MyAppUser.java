package com.tresorerie.voyage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.*;



import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class MyAppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(min = 3, max = 50, message = "Le nom d'utilisateur doit contenir entre 3 et 50 caractères.")
    private String username;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "Veuillez fournir une adresse email valide.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, max = 100, message = "Le mot de passe doit contenir entre 8 et 100 caractères.")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de rôle est obligatoire.")
    private RoleType roleType;


}