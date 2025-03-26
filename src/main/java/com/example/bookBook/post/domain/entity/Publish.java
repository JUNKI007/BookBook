package com.example.bookBook.post.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publish")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Post post;

    public Publish(Book book, Post post) {
        this.book = book;
        this.post = post;
    }
}
