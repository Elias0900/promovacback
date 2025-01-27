package com.tresorerie.voyage.jwt;

import com.tresorerie.voyage.model.RoleType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtHelper implements Serializable {

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;


    private static final String SECRET_KEY_STRING = "98da37984d88cf0f89b291499e9d0f0ff5d2f5f94c4621344d11bcac668ea9415e8c54f9dc4a5cecc9ca0a88b1f007070261c98e2dc2877a2fcf6ea45d9b89014630df7c939873e9c7c4776d04a8173498e26eb834ec744528739e54c21cb55e836b928f3e052b520cfb5aa13c58573d565089bb15cd6bad2182ec9b16034d3766e95df42b0e76a55698bc2160a045eb14f5660ee2b7a909fb4e302e8007db4b089e7bf7abd57d20f32b38be4f8107eedac9cc295c6c67ad402bc9239c36aa1ecfca15e24fcc6e3272b68837532e95c33b276d2ffee237829c461f8c87b21edbfd4337e5ef0944ec7ec35c0df07351c62db3e085bcc1f14912373acb1ebc5898";
    // Vous pouvez charger cette clé à partir d'un fichier de configuration ou des variables d'environnement, mais l'exemple montre l'utilisation directe.

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8)); // Correct


    private static final int EXPIRATION_MINUTES = 60;


    /**
     * Génère un token JWT avec l'email et le rôle de l'utilisateur.
     */
    public static String generateToken(String email, RoleType roleType) {
        var now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .claim("Role", roleType) // Ajouter le rôle dans le token
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(EXPIRATION_MINUTES, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Signer avec la clé secrète
                .compact();
    }

    /**
     * Extrait le username (email) du token.
     */
    public static String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * Extrait le rôle de l'utilisateur à partir du token.
     */
    public static RoleType extractRole(String token) {
        String role = getTokenBody(token).get("role", String.class);
        return RoleType.valueOf(role); // Convertir la chaîne en RoleType (Enum)
    }

    /**
     * Valide le token en vérifiant qu'il correspond à l'utilisateur et qu'il n'est pas expiré.
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Récupère les claims (contenu) du token.
     */
    private static Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // Récupérer le "body" des claims
        } catch (ExpiredJwtException e) {
            throw new AccessDeniedException("Token expiré : " + e.getMessage());
        } catch (SignatureException e) {
            throw new AccessDeniedException("Signature invalide : " + e.getMessage());
        }
    }

    /**
     * Vérifie si le token est expiré.
     */
    private static boolean isTokenExpired(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }
}
