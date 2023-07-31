package com.example.brunchStory.post.domain.entity;

import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Likes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;
    @ManyToOne
    private Post post;
}
