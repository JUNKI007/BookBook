package com.example.brunchStory.post.controller;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.post.domain.request.CommentRequest;
import com.example.brunchStory.post.domain.response.CommentResponse;
import com.example.brunchStory.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private final AuthService authService;


    @PostMapping("/post/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER', 'ROLE_ADMIN')")
    public void insertComment(@RequestBody CommentRequest commentRequest,
                              @PathVariable Long postId,
                              @RequestHeader("Authorization") String token) {
        Map<String, Object> claims = authService.getClaims(token);
        Long memberId = ((Integer) claims.get("memberId")).longValue();
        commentService.insertComment(commentRequest, postId, memberId);
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER', 'ROLE_ADMIN')")
    public void deleteComment(@PathVariable Long commentId,
                              @RequestHeader("Authorization") String token) {
        Map<String, Object> claims = authService.getClaims(token);
        Long memberId = ((Integer) claims.get("memberId")).longValue();
        String role = (String) claims.get("role");
        commentService.deleteComment(commentId, memberId, role);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER', 'ROLE_ADMIN')")
    public List<CommentResponse> getAllCommentByMember(@RequestHeader("Authorization") String token) {
        Map<String, Object> claims = authService.getClaims(token);
        Long memberId = ((Integer) claims.get("memberId")).longValue();
        return commentService.getAllCommentByMember(memberId);
    }
}

