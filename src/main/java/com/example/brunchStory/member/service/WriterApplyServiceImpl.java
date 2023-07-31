package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.repository.WriterApplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WriterApplyServiceImpl implements WriterApplyService {
    private final WriterApplyRepository writerApplyRepository;
    // MemberService 를 받아와야 할듯 .. 작가로 등록하는 메서드

    // 작가 신청 정보 저장
    @Override
    public void saveWriterApply(String title, String content, LocalDate localDate, Member applicant) {
        WriterApply writerApply = new WriterApply();
        writerApply.setTitle(title);
        writerApply.setContent(content);
        writerApply.setLocalDate(localDate);
        writerApply.setApplicant(applicant);
        writerApplyRepository.save(writerApply);
    }

    // 작가 신청 정보를 조회(Id로 조회)
    @Override
    public WriterApply getWriterApplyById(Long id) {
        return writerApplyRepository.findById(id).orElse(null);
    }

    //작가 신청 정보를 조회(작가 신청자로 조회)
    @Override
    public List<WriterApply> getWriterAppliesByApplicant(Member applicant) {
        return writerApplyRepository.findByApplicant(applicant);
    }

    //모든 작가 신청 정보를 조회
    @Override
    public List<WriterApply> getAllWriterApplies() {
        return writerApplyRepository.findAll();
    }

    //작가 신청 정보를 삭제
    @Override
    public void deleteWriterApply(Long id) {
        writerApplyRepository.deleteById(id);
    }

    @Override
    public void approveWriterApply(Long id) {
        WriterApply writerApply = getWriterApplyById(id);
        if(writerApply != null) {
            Member applicant = writerApply.getApplicant();

            //작가로 등록하는 메서드는 MemberService에 추가로 구현?

        }
    }
}
