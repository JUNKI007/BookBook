package com.example.brunchStory.post.domain.dto;

import com.example.brunchStory.post.domain.entity.Subject;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostCondition {

    private String title;
    private String content;
    private Integer likeGoe;
    private Integer likeLoe;
    private List<Long> interest; // 이건 잠시 대기
}
