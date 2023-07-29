package com.example.brunchStory.member.domain.entity;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.post.domain.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "members")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    /// 멤버와 작가 구분 선

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @OneToMany(mappedBy = "author")
    private List<Subscribe> publishers;


}
