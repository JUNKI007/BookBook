package com.example.bookBook.config.domain.dto;

import com.example.bookBook.post.domain.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookDto {
    private Long id;

    private String bookName;

    private Integer page;

    private LocalDate publishedDate;

    public BookDto(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.page = book.getPage();
        this.publishedDate = book.getPublishedDate();
    }
}
