package com.example.brunchStory.post.domain.event;

import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.context.ApplicationEvent;

public class PostCreateEvent extends ApplicationEvent {

    private final Post post;

    public PostCreateEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}