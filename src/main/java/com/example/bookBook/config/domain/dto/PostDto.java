package com.example.bookBook.config.domain.dto;

import com.example.bookBook.post.domain.entity.Post;
import com.example.bookBook.post.domain.entity.Publish;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;

    private int likeCount;

    public  PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
    }

    public PostDto(Publish publish){
        this.id = publish.getPost().getId();
        this.title = publish.getPost().getTitle();
        this.content = publish.getPost().getContent();
        this.likeCount = publish.getPost().getLikeCount();
    }


}
