package com.example.brunchStory.post.domain.request;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Comment;
import com.example.brunchStory.post.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private String content;
    private Long memberId;

    public Comment toEntity(Post post, Member member){
        return Comment.builder()
                .content(content)
                .userName(member.getName())
                .post(post)
                .member(member) // Add the member information here
                .commentTime(LocalDateTime.now())
                .build();
    }
}
