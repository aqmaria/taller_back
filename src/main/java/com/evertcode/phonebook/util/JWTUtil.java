package com.evertcode.phonebook.util;

import com.evertcode.phonebook.config.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Autowired
    SecurityProperties securityProperties;
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100))
                .signWith(SignatureAlgorithm.HS256,securityProperties.getJwtSignatureKey())
                .compact();
    }

    public boolean validateToken(String token,UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(securityProperties.getJwtSignatureKey()).parseClaimsJws(token).getBody();
    }
}
