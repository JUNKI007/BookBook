package com.example.bookBook.post.domain.response;

import com.example.bookBook.config.domain.dto.BookDto;
import com.example.bookBook.config.domain.dto.PostDto;
import com.example.bookBook.post.domain.entity.Book;

import java.util.List;

public class BookResponse extends BookDto {

//    private MemberDto memberDto;
    private List<PostDto> postDtos;

    public BookResponse(Book book){
        super(book);
//        this.memberDto = new MemberDto(book.getAuthor());
        this.postDtos = book.getPublishedPosts().stream().map(PostDto::new).toList();
    }
}
