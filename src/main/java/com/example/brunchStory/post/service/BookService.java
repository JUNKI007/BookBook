package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Publish;
import com.example.brunchStory.post.domain.request.BookRequest;

import com.example.brunchStory.post.domain.response.BookResponse;
import com.example.brunchStory.post.repository.BookRepository;
import com.example.brunchStory.post.repository.PublishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublishRepository publishRepository;
    private final PostService postService;




    public void publish(BookRequest bookRequest,Long memberId ){
        //글이 자신의 것인지 재확인 필요함.

        Member member = Member.builder().id(memberId).build();

        Book book = bookRepository.save(bookRequest.toEntity(member));

        List<Post> postsById = postService.findAllById(bookRequest.getPosts());
        // findbyidandmember로 하는게 낫지않나 생각중.
        // 왜냐면 자신의 글만 가져올 수 있어야함.

        List<Publish> publishes = new ArrayList<>();

        for (Post post:
             postsById) {
           // 포스트마다 현재 로그인 된 사람인지 검증 필요. 근데 그걸 여기서 해야하나?
            // 메소드를 만들어서 검증하는게 낫다.
            Publish publish = new Publish(null,post,book);
            publishes.add(publish);
        }

        publishRepository.saveAll(publishes);

    }

    public BookResponse read(Long BookId){

        Optional<Book> byId = bookRepository.findByIdCustom(BookId);

        Book book = byId.orElseThrow(RuntimeException::new);
        // n+1 해결해야함.

        //이걸 북리스폰스로 바꿔줘야함.
        return new BookResponse(book);
    }

}
