package com.example.brunchStory.post.controller;

import com.example.brunchStory.post.domain.request.BookRequest;
import com.example.brunchStory.post.domain.response.BookResponse;
import com.example.brunchStory.post.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    //책 발간 (여러 게시글을 묶어서 책으로 발행)
    @PostMapping("/publish")
    public ResponseEntity<Void> publishBook(@RequestBody BookRequest bookRequest) {
        bookService.publish(bookRequest);
        return ResponseEntity.ok().build();
    }


    //  특정 책 조회
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.read(bookId));
    }
}
