package com.example.brunchStory.config.domain.dto;

import com.example.brunchStory.member.domain.entity.WriterApply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WriterApplyDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate localDate;
    private boolean approved;

    public WriterApplyDto(WriterApply writerApply){
        this.id = writerApply.getId();
        this.title = writerApply.getTitle();
        this.content = writerApply.getContent();
        this.localDate = writerApply.getLocalDate();
        this.approved = writerApply.isApproved();
    }
}
