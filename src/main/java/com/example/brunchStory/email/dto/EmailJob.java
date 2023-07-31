package com.example.brunchStory.email.dto;

import com.example.brunchStory.email.service.EmailService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailJob implements Job {

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String email = jobDataMap.getString("email");
        String title = jobDataMap.getString("title");

        try {
//            emailService.sendForSubscriber(email, title);
            //X 구독한 작가가 글을 쓸때 발송하는 메소드 완성 시 추가
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
