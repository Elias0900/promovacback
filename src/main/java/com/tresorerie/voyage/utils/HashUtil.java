package com.tresorerie.voyage.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hacherMotDePasse(String motDePasse) throws NoSuchAlgorithmException {
        // Utilisation de SHA-256 pour hacher le mot de passe
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(motDePasse.getBytes());

        // Conversion du tableau de bytes en une chaîne hexadécimale
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
