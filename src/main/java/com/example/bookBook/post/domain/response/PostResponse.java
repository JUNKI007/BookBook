package com.example.bookBook.post.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private int likeCount;
    private String authorName;  // Member 대신 String으로
    private List<String> subjects;  // Subject 대신 이름만
}
