package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubScribeRepository extends JpaRepository<Subscribe, Long> {
    boolean existsByMemberIdAndAuthorId(Long memberId, Long authorId);

    List<Subscribe> findByMemberIdAndAuthorId(Long memberId, Long authorId);
    void deleteMemberIdAndAuthorId(Long memberId, Long authorId);
    Long countByAuthorId(Long authorId);
}
