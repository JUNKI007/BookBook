package com.example.brunchStory.post.controller;

import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.example.brunchStory.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 📌 게시글 작성
    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody PostRequest postRequest,
            @RequestParam Long memberId) {
        postService.write(memberId, postRequest);
        return ResponseEntity.ok().build();
    }

    // 📌 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestParam Long memberId) {
        postService.delete(postId, memberId);
        return ResponseEntity.ok().build();
    }

    // 📌 특정 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    // 📌 모든 게시글 조회 (페이징 가능)
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(postService.findAllByCondition(null, pageRequest));
    }
}
