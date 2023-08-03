package com.example.brunchStory.email.service;


import com.example.brunchStory.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableAsync
public class EmailService {


    private final MailSender mailSender;


    @Async
    public void send(String title, String email,String content){
        SimpleMailMessage msg = new SimpleMailMessage();

        //TODO 멤버에서 이메일 찾아다가 보내줘야함.
        // 그 멤버들은 작가가 글을 썼을 때 알람설정을 한 대상자들
        // 즉 현재 로그인 한 멤버에서 구독자를 가져와서 구독자들의 이메일을 수집해서 Set으로 저장해두기?
        // 그 Set 을 보내는 형식으로 하면 되나 고민중.
        // 보내는 것 자체는 여기서 하지만, 위와같은 과정은 다른곳에 존재햐아함.

        msg.setTo(email);
        msg.setSubject(title);
        msg.setText(content);


        mailSender.send(msg);
    }
}
