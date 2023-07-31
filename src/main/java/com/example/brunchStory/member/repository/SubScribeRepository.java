package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubScribeRepository extends JpaRepository<Subscribe, Long> {
    boolean existsByMemberAndAuthor(Member member, Member author);

    Subscribe findByMemberAndAuthor(Member member, Member author);

    int countByAuthor(Member author);
}
