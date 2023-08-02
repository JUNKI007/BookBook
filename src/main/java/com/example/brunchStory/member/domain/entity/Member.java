package com.example.brunchStory.member.domain.entity;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.post.domain.entity.*;
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

    @Column(unique = true,nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole role;


    @OneToMany(mappedBy = "member")
    private List<Alarm> tellNewPost;

    @OneToMany(mappedBy = "member")
    private List<Subscribe> publishers;

    @OneToMany(mappedBy = "member")
    private List<Interest> interests;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    /// 멤버와 작가 구분 선

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @OneToMany(mappedBy = "author")
    private List<Subscribe> subscribers;





}
