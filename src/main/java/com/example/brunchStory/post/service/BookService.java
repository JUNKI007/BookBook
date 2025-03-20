package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.repository.MemberRepository;
import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Publish;
import com.example.brunchStory.post.domain.request.BookRequest;
import com.example.brunchStory.post.domain.response.BookResponse;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.example.brunchStory.post.repository.BookRepository;
import com.example.brunchStory.post.repository.PostRepository;
import com.example.brunchStory.post.repository.PublishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PublishRepository publishRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostService postService;




    @Transactional
    public void publish(BookRequest bookRequest) {
        Member member = memberRepository.findById(bookRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Book book = bookRequest.toEntity(member);
        bookRepository.save(book);

        List<Post> posts = postRepository.findAllById(bookRequest.getPostIds());

        for (Post post : posts) {
            if (post.getPublishes() == null) {
                post.updatePublishes(new ArrayList<>());
            }
            Publish newPublish = new Publish(book, post);
            post.getPublishes().add(newPublish);
            publishRepository.save(newPublish);
        }
    }


    public BookResponse read(Long BookId){

        Optional<Book> byId = bookRepository.findById(BookId);
        // n+1 해결해야함.

        Book book = byId.orElseThrow(RuntimeException::new);
        //이걸 북리스폰스로 바꿔줘야함.
        return new BookResponse(book);
    }
}
