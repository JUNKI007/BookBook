package com.example.brunchStory.config.auth;

import com.example.brunchStory.member.domain.dto.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }


    private String extractTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    //X 이 메소드는 JWT토큰을 포함하고 있을 것으로 예상되는 'Authorization'헤더값을 Http헤더에서 추출하는 것


    private Authentication getAuthentication(String token) {
        Map<String, Object> claims = authService.getClaims(token);
        MemberRole role = MemberRole.valueOf((String) claims.get("role"));
        return new UsernamePasswordAuthenticationToken(claims, "", Collections.singletonList(role));
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = extractTokenFromHeader(httpServletRequest);

        if (StringUtils.hasText(token)) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
    //X ServletRequest를 HttpServletRequest로 다운캐스팅하여, HTTP 요청과 관련된 추가 메소드에 접근할 수 있게 합니다.




}