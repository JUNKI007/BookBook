package com.example.bookBook.post.domain.request;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.post.domain.entity.Post;
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
