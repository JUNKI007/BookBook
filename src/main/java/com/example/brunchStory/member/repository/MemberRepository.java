package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserIdAndPassword(String userId, String password);

}
