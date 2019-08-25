package com.dbc.prova.security.util;

import com.dbc.prova.property.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final Log lOGGER = LogFactory.getLog(JWTUtil.class);

    private String secret;
    private Long expiration;

    public JWTUtil(JWTProperty jwtProperty) {
        this.secret = jwtProperty.getSecret();
        this.expiration = jwtProperty.getExpiration();
    }

    public String generateToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean validateToken(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            return username != null && expirationDate != null && now.before(expirationDate);
        }

        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);

        return claims != null ? claims.getSubject() : null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            lOGGER.debug("Error on get claims!");

            return null;
        }
    }

}
