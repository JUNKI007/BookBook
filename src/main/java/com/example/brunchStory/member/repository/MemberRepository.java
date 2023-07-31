package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
