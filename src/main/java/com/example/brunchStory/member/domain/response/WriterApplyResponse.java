package com.example.brunchStory.member.domain.response;

import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.WriterApplyDto;
import com.example.brunchStory.member.domain.entity.WriterApply;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WriterApplyResponse extends WriterApplyDto {
    private MemberDto applicant;

    public WriterApplyResponse(WriterApply writerApply) {
        super(writerApply);
        this.applicant = new MemberDto(writerApply.getApplicant());
    }
}
