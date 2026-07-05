package com.aravindh.gymtracker.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;



@Service
public class JwtService {
    private static final String SECRET_KEY =
        "mySuperSecretKeyForGymTrackerApplication123456789";
    
        private final SecretKey key =
        Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
        public String generateToken(String email) {

            return Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(
                        new Date(
                            System.currentTimeMillis()
                            + 1000 * 60 * 60 * 24
                        )
                    )
                    .signWith(key)
                    .compact();
        }
        public String extractEmail(String token) {

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
        public boolean isValid(String token){
            try {
                extractEmail(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
}
