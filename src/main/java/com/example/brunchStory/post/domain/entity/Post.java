package com.example.brunchStory.post.domain.entity;

import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private int likeCount;

    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "post")
    private List<Publish> publishes;

    @OneToMany(mappedBy = "post")
    private List<Likes> likes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
