package com.cooba.service;

import com.cooba.exception.TokenException;
import com.cooba.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class TokenService {
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("THIS-IS-SPRING-BOOT-SECURITY-SAMPLE".getBytes());

    public String generateToken(UserDetails userDetails) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusMinutes(30);
        Claims claims = Jwts
                .claims(Map.of(
                        "name", userDetails.getUsername()
                ))
                .setIssuedAt(DateUtils.asDate(now))
                .setExpiration(DateUtils.asDate(expireTime));

        return Jwts.builder().setClaims(claims).signWith(secretKey).compact();
    }

    public Claims parseToken(String token) throws TokenException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TokenException();
        }
    }
}
