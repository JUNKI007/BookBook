package com.example.bookBook.post.service;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.post.domain.dto.PostCondition;
import com.example.bookBook.post.domain.entity.Post;
import com.example.bookBook.post.repository.PostRepository;
import com.example.bookBook.post.domain.request.PostRequest;
import com.example.bookBook.post.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;





    public void write(Long memberId, PostRequest postRequest){
        //TODO 멤버 아이디로 멤버 찾아오기
        Member member = Member.builder().id(memberId).build();

        Post post = postRequest.toEntity(member);
        postRepository.save(post);

    }

    // 게시글 삭제
    public void delete(Long postId, Long memberId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (!post.getAuthor().getId().equals(memberId)) {
                throw new IllegalArgumentException("삭제할 권한이 없습니다.");
            }
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }

    public List<Post>findAllById(List<Long> postIds){ // 아이디들로 모든 글 찾아오기
        List<Post> allById = postRepository.findAllById(postIds);
        return allById;
    }

    public Post findById(Long postId){ // 글 하나 찾아오기.
        Optional<Post> byId = postRepository.findById(postId);

        Post post = byId.orElseThrow(RuntimeException::new);

        return post;
    }

    @Transactional(readOnly = true)
    public Page<PostResponse> findAllByCondition(PostCondition postCondition, PageRequest pageRequest){
        return postRepository.findAllByCondition(pageRequest,postCondition);
    }
    @Transactional(readOnly = true)
    public Page<PostResponse> findAllByFavorite(Integer memberId, PageRequest pageRequest){
        // 멤버 아이디로 멤버 찾아서 그 유저의 흥미 뽑아오기 ( 리스트로 )
        // 그 흥미에 맞게 글을 찾아와야하는데, 중복이 되면 안됨.
        return  null;
    }


}
