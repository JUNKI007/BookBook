package com.example.brunchStory.post.domain.entity;

import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "publish")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Post post;

    @ManyToOne
    private Book book;
}
