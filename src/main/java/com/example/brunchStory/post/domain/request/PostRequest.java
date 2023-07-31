package com.example.brunchStory.post.domain.request;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;


    public Post toEntity(Member member){
        return Post.builder()
                .title(title)
                .content(content)
                .author(member)
                .build();

    }
}
