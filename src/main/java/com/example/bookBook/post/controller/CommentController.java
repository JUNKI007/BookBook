package com.example.bookBook.post.controller;

import com.example.bookBook.aspect.TokenRequired;
import com.example.bookBook.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 (인증 필요)
    @PostMapping
    @TokenRequired
    public ResponseEntity<Void> writeComment(
            @RequestParam Long postId,
            @RequestParam Long memberId,
            @RequestParam String content
    ) {
        commentService.write(postId, memberId, content);
        return ResponseEntity.ok().build();
    }
}
