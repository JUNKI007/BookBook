package com.example.bookBook.post.service;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.post.domain.entity.Likes;
import com.example.bookBook.post.domain.entity.Post;
import com.example.bookBook.post.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostService postService;
    public void like(Long memberId, Long postId){
        Member member = Member.builder().id(memberId).build();
        Post post = postService.findById(postId);
        Likes likes = new Likes();
        likes.setMember(member);
        likes.setPost(post);
        likesRepository.save(likes);
    }
    public void  unlike(Long memberId, Long postId){
        Member member = Member.builder().id(memberId).build();
        Post post = postService.findById(postId);
        Likes likes =  likesRepository.findByMemberAndPost(member, post);
        if (likes != null){
            likesRepository.delete(likes);
        }else {
            throw new IllegalArgumentException("좋아요를 취소할 정보가 없습니다.");
        }
    }

    public boolean hasLiked(Long memberId, Long postId){
        Member member = Member.builder().id(memberId).build();
        Post post = postService.findById(postId);
        return likesRepository.existsByMemberAndPost(member, post);
    }

    public Long getLikeCount(Long postId){
        Post post = postService.findById(postId);
        return likesRepository.countByPost(post);
    }
}
