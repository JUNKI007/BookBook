package com.example.brunchStory.config.domain.entity;

import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member_login")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate loginTime;

    @ManyToOne
    private Member memberLog;

}
