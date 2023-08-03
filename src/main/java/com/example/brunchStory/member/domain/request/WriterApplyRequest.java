package com.example.brunchStory.member.domain.request;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriterApplyRequest {
    private String title;
    private String content;

    public WriterApply toEntity(Member applicant){
        return WriterApply.builder().content(content)
                .title(title).applicant(applicant).localDate(LocalDate.now()).approved(false).build();
    }
}

