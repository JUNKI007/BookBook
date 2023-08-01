package com.example.brunchStory.aspect;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.config.exception.EmptyTokenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {

    @Autowired
    private AuthService authService;

    @Around("@annotation(TokenRequired)")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {

        ServletRequestAttributes request =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String token = request.getRequest().getHeader("Authorization");
        System.out.println(token);

        if(token == null) throw  new EmptyTokenException("토큰이 필요합니다");
        String replace = token.replace("Bearer ", "");
        authService.getClaims(replace);

        Object proceed = point.proceed();

        return proceed;
    }
}
