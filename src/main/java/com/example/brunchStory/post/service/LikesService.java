package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Likes;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostService postService;
    public void Like(Long memberId, Long postId){
        Member member = Member.builder().id(memberId).build();
        Post post = postService.findById(postId);
        Likes existingLike = likesRepository.findByMemberAndPost(member, post);
        if (existingLike != null){
            likesRepository.delete(existingLike);
            post.setLikeCount(post.getLikeCount() - 1);

        }else {
            Likes likes = new Likes();
            likes.setMember(member);
            likes.setPost(post);
            likesRepository.save(likes);
            post.setLikeCount(post.getLikeCount() + 1);
        }
//        postService.savePost(post);
    }


    public boolean hasLiked(Long memberId, Long postId){
        Member member = Member.builder().id(memberId).build();
        Post post = postService.findById(postId);
        return likesRepository.existsByMemberAndPost(member, post);
    }


}
