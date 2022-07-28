package io.desofme.authservice.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtManager {

    @Value("${secret.key}")
    private String SECRET_KEY;
    @Value("${expired.time}")
    private long EXPIRED_TIME;

    public String generateToken(Long id){
        return Jwts.builder().setSubject(String.valueOf(id))
                .setIssuer("io.desofme")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(EXPIRED_TIME)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }




}
