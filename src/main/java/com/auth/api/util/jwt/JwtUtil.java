package com.auth.api.util.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
public class JwtUtil {

    @Value("${authApi.secKey}")
    private String SECRET_KEY;

    @Value("${authApi.sessionTime}")
    private String SESSION_TIME;

    public String generateToken(String username) {
        long expirationTime = Long.parseLong(SESSION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("Invalid token: {}", e.getMessage());
        }
        return false;
    }
}
