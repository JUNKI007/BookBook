package com.example.bookBook.post.controller;

import com.example.bookBook.aspect.TokenRequired;
import com.example.bookBook.post.domain.request.BookRequest;
import com.example.bookBook.post.domain.response.BookResponse;
import com.example.bookBook.post.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/publish")
    @TokenRequired
    public ResponseEntity<Void> publishBook(@RequestBody BookRequest bookRequest) {
        bookService.publish(bookRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookId}")
    @TokenRequired
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.read(bookId));
    }

}
