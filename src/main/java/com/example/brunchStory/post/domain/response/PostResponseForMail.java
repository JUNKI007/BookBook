package com.example.brunchStory.post.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.post.domain.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseForMail extends PostDto {

    private Long subjectNum;

    public PostResponseForMail(Post post) {
        super(post);
        this.subjectNum = post.getSubject().getId();
    }
}
