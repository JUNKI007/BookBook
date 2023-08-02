package com.example.brunchStory.post.domain.request;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private Long subjectNum;


    public Post toEntity(Member member, Subject subject){
        return Post.builder()
                .title(title)
                .content(content)
                .author(member)
                .subject(subject)
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
