package com.example.bookBook.post.repository;

import com.example.bookBook.post.domain.dto.PostCondition;
import com.example.bookBook.post.domain.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomPostRepository {

    Page<PostResponse> findAllByCondition(
            PageRequest request,
            PostCondition condition
    );
}
