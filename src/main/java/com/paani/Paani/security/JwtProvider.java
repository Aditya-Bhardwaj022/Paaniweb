package com.paani.Paani.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;


@Component
public class JwtProvider {
    @Value("${app.jwtSecret}") private String jwtSecret;
    @Value("${app.jwtExpirationMs}") private int jwtExpirationMs;


    private Key key() { return Keys.hmacShaKeyFor(jwtSecret.getBytes()); }


    public String generateToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiry).signWith(key()).compact();
    }


    public String getUsernameFromJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token) {
        try { Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }
}