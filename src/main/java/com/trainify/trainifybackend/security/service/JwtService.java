package com.trainify.trainifybackend.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // 🔑 Tajny klucz do podpisywania tokenów.
    // Powinien być przechowywany w application.properties i być silny (min. 256 bit, czyli 32 znaki base64).
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    // ⏱ Czas wygaśnięcia tokena (np. 24 godziny)
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    // --- Metody publiczne ---

    // 1. Ekstrakcja nazwy użytkownika (email) z tokena
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 2. Generowanie tokena dla UserDetails (użytkownik)
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // 3. Sprawdzenie, czy token jest ważny dla danego użytkownika
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    // --- Metody prywatne (pomocnicze) ---

    // Ekstrakcja pojedynczej 'claims' (danych) z tokena
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Generowanie tokena z dodatkowymi danymi ('extraClaims')
    private String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder() // Rozpoczęcie budowania tokena
                .setClaims(extraClaims) // Dodatkowe claims
                .setSubject(userDetails.getUsername()) // Temat (zazwyczaj nazwa użytkownika/email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Czas utworzenia
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) // Czas wygaśnięcia
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Podpisanie kluczem SHA-256
                .compact(); // Zbudowanie i zwrócenie jako String
    }

    // Sprawdzenie, czy token wygasł
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Ekstrakcja daty wygaśnięcia
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Ekstrakcja wszystkich 'claims' (danych) z tokena
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Uruchomienie parsera
                .setSigningKey(getSignInKey()) // Użycie tajnego klucza
                .build()
                .parseClaimsJws(token) // Parsowanie tokena
                .getBody(); // Pobranie zawartości (claims)
    }

    // Pobranie tajnego klucza do podpisu
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Dekodowanie klucza z Base64
        return Keys.hmacShaKeyFor(keyBytes); // Zbudowanie klucza
    }
}