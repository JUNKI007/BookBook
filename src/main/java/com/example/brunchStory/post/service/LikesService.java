package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Post;

public interface LikesService {
    void like(Member member, Post post);

    void  unlike(Member member, Post post);

    boolean hasLiked(Member member, Post post);

    int getLikeCount(Post post);
}
