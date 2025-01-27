package com.tresorerie.voyage.config;

import com.tresorerie.voyage.model.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse extends ApiResponse {
    private String token;
    private RoleType role;
    private String email;
    private Long userId;

    public AuthResponse(boolean success, String message, String token, RoleType role, String email, Long id) {
        super(success, message);
        this.token = token;
        this.role = role;
        this.email = email;
        this.userId = id;
    }


    // Getters et Setters...
}