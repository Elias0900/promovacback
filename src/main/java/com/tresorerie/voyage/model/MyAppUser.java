package com.tresorerie.voyage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@ToString(exclude = "transaction") // Exclut 'ventes' de la méthode toString
public class MyAppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType; //


    @OneToMany(mappedBy = "user")
    private List<Vente> transaction;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Bilan bilan; // Relation avec le bilan

    public void setBilan(Bilan bilan) {
        this.bilan = bilan;
        if (bilan != null) {
            bilan.setUser(this);  // Assurez-vous que le bilan référence correctement l'utilisateur
        }
    }
}