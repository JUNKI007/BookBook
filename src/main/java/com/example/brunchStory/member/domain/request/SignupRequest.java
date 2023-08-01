package com.example.brunchStory.member.domain.request;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.member.domain.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SignupRequest(
                            String userId,
                            String password,
                            String name,
                            String email
                            ) {
    public Member toEntity(){

        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .role(MemberRole.ROLE_MEMBER)
                .build();
    }



}
