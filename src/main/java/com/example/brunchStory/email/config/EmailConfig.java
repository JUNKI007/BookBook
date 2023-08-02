package com.example.brunchStory.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class EmailConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 기본적으로 유지할 스레드 수
        executor.setMaxPoolSize(20); // 최대 스레드 수 (큐가 꽉 찼을 때 추가 생성)
        executor.setQueueCapacity(30); // 작업 큐 사이즈
        executor.setThreadNamePrefix("MyExecutor-"); // 스레드 이름 접두사
        executor.initialize(); // 초기화
        return executor;
    }
}
