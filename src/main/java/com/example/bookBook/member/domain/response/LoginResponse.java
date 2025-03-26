package com.example.bookBook.member.domain.response;

import com.example.bookBook.member.domain.dto.MemberRole;

public record LoginResponse(
        Long id, String email, MemberRole role, String token ) {
}
