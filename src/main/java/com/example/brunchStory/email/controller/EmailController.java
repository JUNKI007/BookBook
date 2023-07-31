//package com.example.brunchStory.email.controller;
//
//import com.example.brunchStory.email.service.EmailService;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmailController {
//
//    private final EmailService emailService;
//
//    public EmailController(EmailService emailService){
//        this.emailService = emailService;
//    }
//
//    @Scheduled(cron = "0 0 9 * * ?")
//    public void sendEmailAtNine() {
//        emailService.sendEmailBySubject();
//    }
//    // 매일 9시에 메일 보내는것. 일단 임의로 sendEmailBySubject로 해둠
//
//}
