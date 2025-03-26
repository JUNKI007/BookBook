package com.example.bookBook.member.domain.entity;

import com.example.bookBook.member.domain.dto.MemberRole;
import com.example.bookBook.post.domain.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "members")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole role;

    @OneToMany(mappedBy = "member")
    private List<Alarm> tellNewPost;

    @OneToMany(mappedBy = "member")
    private List<Subscribe> subscribers;

    @OneToMany(mappedBy = "member")
    private List<Interest> interests;

    /// 멤버와 작가 구분 선

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @OneToMany(mappedBy = "author")
    private List<Subscribe> publishers;


}
