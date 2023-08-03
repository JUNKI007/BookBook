package com.example.brunchStory.post.domain.event;


import com.example.brunchStory.email.service.EmailService;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.entity.Post;
import com.google.gson.Gson;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostCreateEventListener implements ApplicationListener<PostCreateEvent> {

    private final EmailService emailService;
    private final MemberService memberService;

    public PostCreateEventListener(EmailService emailService, MemberService memberService) {
        this.emailService = emailService;
        this.memberService = memberService;
    }

    @Override
    public void onApplicationEvent(PostCreateEvent event) {
        Post post = event.getPost();
        String title = post.getTitle();
        String content = post.getContent();
        String authorName = post.getAuthor().getName(); // 이건 쿼리로 갖고와야해서 못가져옴.
        // 나머진 다 post 에 있는데
        LocalDateTime createTime = post.getLocalDateTime();

        // 이메일 본문 구성
        String subject = "새로운 글이 작성되었습니다: " + title;
        Map<String,String> mailBody = new HashMap<>();
        mailBody.put("작성자",authorName);
        mailBody.put("작성시간",createTime.toString());
        mailBody.put("내용",content);

        String body = new Gson().toJson(mailBody);

        // 구독한 멤버들에게 이메일 보내기
        List<Member> subscribers = memberService.getSubscribingMembers(post.getAuthor().getId());

        for (Member subscriber : subscribers) {
            String subscriberEmail = subscriber.getEmail();
            emailService.send(title,subscriberEmail, body);
        }
    }
}
