package com.example.bookBook.post.domain.request;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.post.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String bookName;
    private List<Long> postIds;
    private Long memberId;

    public Book toEntity(Member member){
        return Book.builder()
                .bookName(bookName)
                .page(postIds.size())
                .publishedDate(LocalDate.now())
                .author(member)
                .build();
    }
}
