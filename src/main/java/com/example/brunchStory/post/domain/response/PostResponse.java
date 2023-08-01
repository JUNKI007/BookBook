package com.example.brunchStory.post.domain.response;

import com.example.brunchStory.config.domain.dto.PostDto;
import com.example.brunchStory.post.domain.entity.Post;

public class PostResponse extends PostDto {
    public PostResponse() {
        super();
    }
    public PostResponse(Post post) {
        super(post);
    }
}
