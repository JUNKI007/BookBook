package com.example.brunchStory.member.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.config.domain.dto.SubjectDto;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Subject;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberResponse extends MemberDto {
//    subscribers,posts

    private List<MemberDto> publishers; // 작가(유튜버)
    private List<SubjectDto> interests; // 관심사


    @QueryProjection
    public MemberResponse(Member member) {
        super(member);
        publishers = member.getPublishers().stream().map(p->new MemberDto(p.getAuthor())).toList();
        interests = member.getInterests().stream().map(m->new SubjectDto(m.getSubject())).toList();

    }

    // 리스폰스1 일반 멤버 : publishers(작가)만 가지고 있다
    // 리스폰스2 작가(author) : 싹다 posts, publishers, subscribers

}