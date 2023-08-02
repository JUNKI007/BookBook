package com.example.brunchStory.config.domain.dto;

import com.example.brunchStory.post.domain.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectDto {
    private Long id;

    private String name;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();

        this.name = subject.getName();
    }
}
