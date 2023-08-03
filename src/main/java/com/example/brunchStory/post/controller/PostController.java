package com.example.brunchStory.post.controller;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.example.brunchStory.post.domain.response.PostResponseForMail;
import com.example.brunchStory.post.service.LikesService;
import com.example.brunchStory.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final AuthService authService;
    private final PostService postService;
    private final LikesService likesService;

    // 글 하나 찾기
    @GetMapping("{id}")
    public PostResponse findPostById(@PathVariable(name = "id") Long postId) {
        return postService.findByIdCustom(postId);
        //
    }

    //글 조건으로 찾기
    @GetMapping
    public Page<PostResponse> findAllByCondition(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            PostCondition condition){
        return postService.findAllByCondition(condition, PageRequest.of(page,size));
    }

    // 글 쓰기
    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public void post(@RequestBody PostRequest postRequest,
                      @RequestHeader("Authorization")String token) {

        Map<String, Object> data = authService.getClaims(token);

        Long memberId = ((Integer) data.get("memberId")).longValue();
        postService.write(memberId,postRequest);
    }

    // 글 지우기
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public void delete(@PathVariable(name = "id") Long postId
            ,@RequestHeader("Authorization")String token) throws SQLIntegrityConstraintViolationException {
        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));

        Long memberId = ((Integer) data.get("memberId")).longValue();
        postService.delete(postId,memberId);
    }
    // 글 메일로 보내기
    @PostMapping("mailtest")
    public void mailService(){
        postService.sendMail();
    } // 이거 하면 메일 감.

    //////////////////

    // 좋아요 하는 것 .
    @PostMapping("/like/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public void likes( @PathVariable Long postId,
                          @RequestHeader("Authorization")String token) {

        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();
        // 잘못된 postId 사용
        likesService.likes(memberId, postId); // postId가 9999인 글은 존재하지 않는다고 가정합니다.
    }
}
