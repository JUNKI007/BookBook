package com.example.brunchStory.config.auth;

import com.example.brunchStory.member.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String makeToken(Member member) {
        SecretKeySpec key = getSecretKeySpec();

        String compact = Jwts.builder()
                .claim("memberId", member.getId())
                .claim("email", member.getEmail())
                .claim("role", member.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + 30_000_000))
                .signWith(key)
                .compact();
        return compact;
    }


    private SecretKeySpec getSecretKeySpec() {
        SignatureAlgorithm hs384 = SignatureAlgorithm.HS384;
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs384.getJcaName());
        return key;
    }

    public Map<String,Object> getClaims(String token){
        SecretKeySpec key = getSecretKeySpec();

        return (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parse(token)
                .getBody();
    }

}