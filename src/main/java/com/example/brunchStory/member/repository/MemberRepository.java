package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserIdAndPassword(String userId, String password);

    @Query("select m from Member m inner join fetch m.publishers")
    Page<Member> findAllBy(Pageable request);
}
