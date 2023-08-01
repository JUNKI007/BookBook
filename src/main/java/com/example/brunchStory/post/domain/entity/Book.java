package com.example.brunchStory.post.domain.entity;

import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private Integer page;

    private LocalDate publishedDate;

    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "book")
    private List<Publish> publishedPosts;
}
