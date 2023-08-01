package com.example.brunchStory.member.domain.dto;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_AUTHOR,
    ROLE_MEMBER;

    @Override
    public String getAuthority() {
        return name();
    }
}
