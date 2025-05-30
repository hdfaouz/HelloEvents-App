package com.enaa.helloevents.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.expiration-time}")
    private Long expirationTime;

    public String generateToken(Map<String, String> claims,String username) {

        return createToken(claims, username);
    }

    private String createToken(Map<String, String> claims, String subject) {

        var token =  Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();

        System.out.println(token);
        return token;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //validate token
    public   boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date()) ;
    }

    private Date extractExpirationDate(String token) {
        return  extiratClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extiratClaim(token, Claims::getSubject);
    }

    private <T> T extiratClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extiratAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extiratAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
