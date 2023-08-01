package com.example.brunchStory.member.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.member.domain.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter

public class MemberResponse extends MemberDto {
//    subscribers,posts
    private List<PostDto> posts;
    private List<MemberDto> publishers;
//    private List<>

    @QueryProjection
    public MemberResponse(Member member) {
        super(member);
        posts = member.getPosts()
                .stream().map(PostDto::new).toList();
        publishers = member.getPublishers().stream().map(p->new MemberDto(p.getMember())).toList();

    }

}