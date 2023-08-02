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
    //X AuthService 클래스 선언하며, 변수로 문자열 secretKey를 선언함. 그 값은 @Value로 가져옴(application.yml으로부터)


    private SecretKeySpec getSecretKeySpec() {
        SignatureAlgorithm hs384 = SignatureAlgorithm.HS384;
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs384.getJcaName());
        return key;
    }
    //X JWT의 헤더, 페이로드, 시그니쳐 중에 시그니쳐 부분을 진행하기위해 사용되는 비밀키를 생성
    //X 시그니쳐(서명)은 생성된 JWT가 변조되지 않은지 확인하는 과정
    //X hs384은 암호화 알고리즘 방식의 이름


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
    //X JWT의 페이로드 = 메타데이터, 클레임이라는 정보들이 모인 것
    //X 토큰은 10분간 유효하도록 해두었고, signWith로 토큰에 시그니쳐(서명)하였고,
    //X compact메소드를 호출하여 모든 설정이 적용된 JWT 생성 및 String타입으로 반환


    public Map<String,Object> getClaims(String token){
        token = token.replace("Bearer ", "");
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parse(token)
                .getBody();
    }
    //X 주어진 JWT 토큰을 파싱하여 토큰에 담겨 있는 claim들을 Map 형태로 반환 -> 이를 통해 토큰발행시 설정한 정보들을 확인할 수 있음

}