package com.example.bookBook.post.domain.entity;

import com.example.bookBook.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String comment;
    private LocalDate commentTime;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Post post;


    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updateCommentTime(LocalDate commentTime) {
        this.commentTime = commentTime;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
