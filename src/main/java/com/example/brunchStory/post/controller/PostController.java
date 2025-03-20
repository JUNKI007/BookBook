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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        Post post = postService.findById(postId);

        // Subject가 null인지 확인 후 처리
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



    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(postService.findAllByCondition(null, pageRequest));
    }
}
