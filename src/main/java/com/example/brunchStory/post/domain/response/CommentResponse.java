package com.example.brunchStory.post.domain.response;

import com.example.brunchStory.post.domain.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String userName;
    private String content;
    private LocalDateTime commentTime;


    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.userName = comment.getUserName();
        this.content = comment.getContent();
        this.commentTime = comment.getCommentTime();
    }
}
