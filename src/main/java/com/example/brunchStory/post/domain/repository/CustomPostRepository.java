package com.example.brunchStory.post.domain.repository;

import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomPostRepository {

    Page<PostResponse> findAllByCondition(
            PageRequest request,
            PostCondition condition
    );
}
