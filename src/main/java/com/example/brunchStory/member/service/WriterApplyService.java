package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.repository.MemberRepository;
import com.example.brunchStory.member.repository.WriterApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WriterApplyService {
    private final WriterApplyRepository writerApplyRepository;
    private final MemberRepository memberRepository;

    public void ApplyWriter(Long memberId, String title, String content){
        Member applicant = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        LocalDate applyDate = LocalDate.now();

        WriterApply writerApply = new WriterApply(memberId, title, content, applyDate,  applicant);

        writerApplyRepository.save(writerApply);
    }

    public Optional<WriterApply> getWriterApplyById(Long id){
        return writerApplyRepository.findById(id);
    }
    public List<WriterApply> getWriterApplyByApplicant(Member applicant){
        return writerApplyRepository.findByApplicant(applicant);
    }

    public List<WriterApply> getAllWriterApply(){
        return writerApplyRepository.findAll();
    }
    public void  deleteWriterApply(Long id){
        writerApplyRepository.deleteById(id);
    }

    public void approveWriterApply(Long applyId){
        WriterApply writerApply = writerApplyRepository.findById(applyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 작가 신청 정보를 찾을 수 없습니다."));

        writerApply.setLocalDate(LocalDate.now());

        Member applicant = writerApply.getApplicant();
        applicant.setRole(MemberRole.ROLE_AUTHOR);
    }


//    void saveWriterApply(String title, String content, LocalDate localDate, Member applicant);
//    WriterApply getWriterApplyById(Long id);
//    List<WriterApply> getWriterAppliesByApplicant(Member applicant);
//    List<WriterApply> getAllWriterApplies();
//    void deleteWriterApply(Long id);
    // 작가 신청 승인 메서드 추가
//    void approveWriterApply(Long id);
}
