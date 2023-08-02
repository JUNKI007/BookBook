package com.example.brunchStory.post.domain.response;

import com.example.brunchStory.config.domain.dto.BookDto;
import com.example.brunchStory.config.domain.dto.MemberDto;
import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Publish;

import java.util.List;

public class BookResponse extends BookDto {

    private MemberDto authorDto;
    private List<PostDto> postDtos;

    public BookResponse(Book book){
        super(book);
      this.authorDto = new MemberDto(book.getAuthor());
        this.postDtos = book.getPublishedPosts().stream().map(PostDto::new).toList();
    }
}

