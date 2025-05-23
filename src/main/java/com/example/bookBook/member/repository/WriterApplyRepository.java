package com.example.bookBook.member.repository;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.member.domain.entity.WriterApply;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NonNullApi
public interface WriterApplyRepository extends JpaRepository<WriterApply, Long> {
    List<WriterApply> findByApplicant(Member applicant);


}

