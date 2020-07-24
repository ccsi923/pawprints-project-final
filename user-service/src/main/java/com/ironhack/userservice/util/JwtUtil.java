package com.ironhack.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${secret.key}")
    private String SECRET_KEY;

    @Value("${spring.application.name}")
    private String name;

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /*
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
     */

    public String extractApplicationName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
     */

    public String generateToken(String appName) {
        return createToken(appName);
    }

    public String createToken(String subject) {
        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
        String applicationName = extractApplicationName(token);
        return (name.equals(applicationName));
    }
}
