package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.domain.request.WriterApplyRequest;

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
    private final MemberService memberService;

    public void applyWriter(WriterApplyRequest request, Long memberId) {
        // WriterApplyRequest로부터 필요한 데이터를 추출하여 엔티티로 변환합니다.
        Member member = Member.builder().id(memberId).build();

        // WriterApply 엔티티 생성 및 저장
        WriterApply writerApply = request.toEntity(member);
        writerApplyRepository.save(writerApply);
    }
//    public List<WriterApply> getAllWriterApply(){
//        return writerApplyRepository.findAll();
//    }
//
//    public Optional<WriterApply> getWriterApplyById(WriterApplyRequest request){
//        return writerApplyRepository.findById(request.toEntity().getId());
////        단일 객체
//    }
//    public List<WriterApply> getWriterApplyByApplicant(Member applicant){
//        return writerApplyRepository.findByApplicant(applicant);
//    }
//
//    public void  deleteWriterApply(Long id){
//        writerApplyRepository.deleteById(id);
//    }
//
//    public void approveWriterApply(Long applyId){
//        WriterApply writerApply = writerApplyRepository.findById(applyId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 작가 신청 정보를 찾을 수 없습니다."));
//        writerApply.setLocalDate(LocalDate.now());
//        Member applicant = writerApply.getApplicant();
//        applicant.setRole(MemberRole.ROLE_AUTHOR);
//    }
}
