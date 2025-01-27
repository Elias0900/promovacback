package com.tresorerie.voyage.model;

import com.tresorerie.voyage.model.MyAppUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupResponse {
    private String token;
    private MyAppUser user;
}