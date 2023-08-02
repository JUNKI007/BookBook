package com.example.brunchStory.post.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.config.domain.dto.SubjectDto;
import com.example.brunchStory.post.domain.entity.Post;
import lombok.Getter;

@Getter
public class PostResponse extends PostDto {
    private MemberDto memberDto;
    private SubjectDto subject;

    public PostResponse(Post post) {
        super(post);
        this.memberDto = new MemberDto(post.getAuthor());
        this.subject = new SubjectDto(post.getSubject());
    }
}
