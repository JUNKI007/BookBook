package com.example.brunchStory.post.controller;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.post.domain.request.BookRequest;
import com.example.brunchStory.post.domain.response.BookResponse;
import com.example.brunchStory.post.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final AuthService authService;

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public BookResponse readBook (@PathVariable(name = "id") Long BookId){

        return bookService.read(BookId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public void publish(@RequestBody BookRequest bookRequest,
                        @RequestHeader("Authorization")String token){

        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();
        bookService.publish(bookRequest,memberId);
    }
}
