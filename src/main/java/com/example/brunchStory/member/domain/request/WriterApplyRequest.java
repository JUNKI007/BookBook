package com.example.brunchStory.member.domain.request;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriterApplyRequest {
    private String title;
    private String content;

    public WriterApply toEntity(){
        return WriterApply.builder().content(content)
                .title(title).build();
    }
}

