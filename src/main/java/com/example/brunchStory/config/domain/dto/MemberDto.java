package com.example.brunchStory.config.domain.dto;
import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import com.example.brunchStory.post.domain.entity.Publish;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private MemberRole role;
    public MemberDto(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
    }


}