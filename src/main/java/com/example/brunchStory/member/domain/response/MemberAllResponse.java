package com.example.brunchStory.member.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.SubjectDto;
import com.example.brunchStory.member.domain.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberAllResponse extends MemberDto{
    private List<SubjectDto> interests; // 관심사
    @QueryProjection
    public MemberAllResponse(Member member) {
        super(member);
        interests = member.getInterests().stream().map(m->new SubjectDto(m.getSubject())).toList();
    }
}
