package com.example.brunchStory.post.domain.entity;

import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private int likeCount;

    private LocalDateTime localDateTime;

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


}
