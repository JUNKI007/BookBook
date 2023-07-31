package com.example.brunchStory.member.domain.response;

import com.example.brunchStory.member.domain.dto.MemberRole;

public record LoginResponse(
        Long id, String name, String email, MemberRole role, String token ) {
}
