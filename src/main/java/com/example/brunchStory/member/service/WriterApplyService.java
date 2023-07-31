package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;

import java.time.LocalDate;
import java.util.List;


public interface WriterApplyService {
    void saveWriterApply(String title, String content, LocalDate localDate, Member applicant);
    WriterApply getWriterApplyById(Long id);
    List<WriterApply> getWriterAppliesByApplicant(Member applicant);
    List<WriterApply> getAllWriterApplies();
    void deleteWriterApply(Long id);
    // 작가 신청 승인 메서드 추가
    void approveWriterApply(Long id);
}
