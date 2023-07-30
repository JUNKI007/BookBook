package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Likes;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.repository.LikesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    @Override
    public void like(Member member, Post post) {
        //이미 좋아요를 누른 경우는 무시
        if(!hasLiked(member, post)) {
            Likes likes = new Likes();
            likes.setMember(member);
            likes.setPost(post);
            likesRepository.save(likes);
        }
    }

    @Override
    public void unlike(Member member, Post post) {
        Likes likes = likesRepository.findByMemberAndPost(member, post);
        if (likes != null){
            likesRepository.delete(likes);
        }
    }

    @Override
    public boolean hasLiked(Member member, Post post) {
        return likesRepository.existsByMemberAndPost(member, post);
    }

    @Override
    public int getLikeCount(Post post) {
        return likesRepository.countByPost(post);
    }
}
