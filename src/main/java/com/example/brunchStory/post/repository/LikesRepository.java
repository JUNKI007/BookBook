package com.example.brunchStory.post.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Likes;
import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByMemberAndPost(Member member, Post post);
    boolean existsByMemberAndPost(Member member, Post post);

    Long countByPost(Post post);
}
