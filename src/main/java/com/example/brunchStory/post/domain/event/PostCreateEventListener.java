package com.example.brunchStory.post.domain.event;


import com.example.brunchStory.email.service.EmailService;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        String authorName = post.getAuthor().getName();
        LocalDateTime createTime = post.getLocalDateTime();

        // 이메일 본문 구성
        String subject = "새로운 글이 작성되었습니다: " + title;
        String body = "작성자: " + authorName + "\n"
                + "작성 시간: " + createTime + "\n"
                + "내용: " + content;

        // 구독한 멤버들에게 이메일 보내기
        List<Member> subscribers = memberService.getSubscribingMembers(post.getAuthor().getId());

        for (Member subscriber : subscribers) {
            String subscriberEmail = subscriber.getEmail();
            emailService.send(subscriberEmail, body);
        }
    }
}
