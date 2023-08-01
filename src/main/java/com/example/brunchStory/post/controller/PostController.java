package com.example.brunchStory.post.controller;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.member.domain.request.LoginRequest;
import com.example.brunchStory.member.domain.response.LoginResponse;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.example.brunchStory.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final AuthService authService;

    private final PostService postService;

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public PostResponse writerTest(@PathVariable(name = "id") Long postId) {
        return postService.findByIdCustom(postId);
        //
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public void post(@RequestBody PostRequest postRequest,
                      @RequestHeader("Authorization")String token) {

        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));

        Long memberId = ((Integer) data.get("memberId")).longValue();
        postService.write(memberId,postRequest);
    }
}
