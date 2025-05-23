package com.example.bookBook.post.domain.entity;

import com.example.bookBook.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "post")
    private List<Publish> publishes;

    @OneToMany(mappedBy = "post")
    private List<Likes> likes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;



    public void updatePublishes(List<Publish> publishes) {
        this.publishes = publishes;
    }
}
