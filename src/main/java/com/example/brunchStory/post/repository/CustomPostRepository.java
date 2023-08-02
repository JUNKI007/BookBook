package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.domain.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CustomPostRepository {

    Page<PostResponse> findAllByCondition(
            PageRequest request,
            PostCondition condition,
            List<Subject> allByIds
    );
}
