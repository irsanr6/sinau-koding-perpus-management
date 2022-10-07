package com.irsan.sinaukoding.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irsan.sinaukoding.model.UserData;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Autowired
    private ObjectMapper objectMapper;

    private static final long serialVersionUID = 7700785500455598524L;
    public static final long JWT_TOKEN_VALIDITY = 24;
    private static final String SECRET = "irsanramadhan";

    private String doGenerateToken(Map<String, Object> claims, UserData userData) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(objectMapper.writeValueAsString(userData))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + (TimeUnit.HOURS.toMillis(JWT_TOKEN_VALIDITY))))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compressWith(CompressionCodecs.GZIP)
                    .compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateToken(UserData userData) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userData);
    }

    public UserData extractToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            if (new Date().after(claims.getBody().getExpiration())) {
                return null;
            }
            return objectMapper.readValue(claims.getBody().getSubject(), UserData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserData userData = extractToken(token);
        String username = userData.getUsername();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
