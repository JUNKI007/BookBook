package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@NonNullApi
public interface WriterApplyRepository extends JpaRepository<WriterApply, Long> {
    WriterApply findByApplicant(Member applicant);


}

