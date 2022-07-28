package io.desofme.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtManager {

    @Value("${secret.key}")
    private String SECRET_KEY;

    public Long getIdFromToken(final String token){
        Claims claims = getClaims(token);
        return Long.valueOf(claims.getSubject());
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).
                parseClaimsJws(token)
                .getBody();
    }

}
