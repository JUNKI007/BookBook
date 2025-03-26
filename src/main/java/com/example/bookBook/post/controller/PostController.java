package com.example.bookBook.post.controller;

import com.example.bookBook.aspect.TokenRequired;
import com.example.bookBook.post.domain.entity.Post;
import com.example.bookBook.post.domain.request.PostRequest;
import com.example.bookBook.post.domain.response.PostResponse;
import com.example.bookBook.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성 (인증 필요)
    @PostMapping
    @TokenRequired
    public ResponseEntity<Void> createPost(
            @RequestBody PostRequest postRequest,
            @RequestParam Long memberId) {
        postService.write(memberId, postRequest);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제 (인증 필요)
    @DeleteMapping("/{postId}")
    @TokenRequired
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestParam Long memberId) {
        postService.delete(postId, memberId);
        return ResponseEntity.ok().build();
    }

    // 게시글 단건 조회 (공개)
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        Post post = postService.findById(postId);

        List<String> subjects = post.getSubject() != null
                ? List.of(post.getSubject().getName())
                : Collections.emptyList();

        PostResponse response = new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getLikeCount(),
                post.getAuthor().getName(),
                subjects
        );

        return ResponseEntity.ok(response);
    }

    // 전체 게시글 조회 (공개)
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(postService.findAllByCondition(null, pageRequest));
    }
}
