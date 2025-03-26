package com.example.bookBook.member.domain.request;

import com.example.bookBook.member.domain.dto.MemberRole;
import com.example.bookBook.member.domain.entity.Member;

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
