package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.response.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserIdAndPassword(String userId, String password);


    @Query("select m from Member m" +
            " left join m.publishers p " +
            " left join m.interests i " +
            "where m.id = :id and m.role = 'ROLE_MEMBER'")
    Optional<Member> findByMember(@Param("id") Long id);


    @Query("select m from Member m " +
            "left join m.publishers p " +
            "left join m.interests i " +
            "left join m.subscribers s " +
            "left join m.posts po " +
            "where m.id = :id and m.role = 'ROLE_AUTHOR'")
    Optional<Member> findByAuthor(@Param("id") Long id);

    @Query("select m from Member m left join fetch m.interests")
    Page<Member> findAllMember(Pageable request);

    @Query("select m from Member m inner join fetch m.publishers")
    Page<Member> findAllBy(Pageable request);


    @Query("select m from Member m left join fetch m.interests")
    List<Member> findAllMemberForMail();


}
