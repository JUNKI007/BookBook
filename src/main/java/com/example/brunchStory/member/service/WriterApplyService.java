package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.dto.MemberRole;
import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.domain.request.WriterApplyRequest;
import com.example.brunchStory.member.repository.WriterApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WriterApplyService {
    private final WriterApplyRepository writerApplyRepository;
    private final MemberService memberService;

    public void saveWriter(WriterApplyRequest request, Long memberId) {
        Member member = Member.builder().id(memberId).build();
        // WriterApply 엔티티 생성 및 저장
        WriterApply writerApply = request.toEntity(member);
        writerApplyRepository.save(writerApply);
    }

    public void deleteWriterApply(Long applicantId, Long memberId) {
        WriterApply writerApply = writerApplyRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 작가 지원 정보를 찾을 수 없습니다."));

        if (writerApply.getApplicant().getId().equals(memberId)) {
            writerApplyRepository.delete(writerApply);
        } else {
            throw new IllegalArgumentException("해당 작가 지원정보를 찾을 수 없습니다.");
        }
    }

    public List<WriterApply> getWriterAppliesForMember(Long memberId) {
        Member member = Member.builder().id(memberId).build();
        // memberId에 해당하는 작가 지원 정보들 조회
        return writerApplyRepository.findByApplicant(member);
    }

    public void approveWriterApply(Long applicantId) {
        WriterApply writerApply = writerApplyRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 작가 지원 정보를 찾을 수 없습니다."));

        // 이미 승인된 경우 예외 처리
        if (writerApply.isApproved()) {
            throw new IllegalArgumentException("이미 승인된 작가 지원 정보입니다.");
        }

        // 작가 승인 처리
        writerApply.setApproved(true);
        writerApplyRepository.save(writerApply);

        // 회원 엔티티 조회
        Member member = writerApply.getApplicant();

        // 회원 등급 변경 (승인 시 ROLE_AUTHOR로 변경)
        member.setRole(MemberRole.ROLE_AUTHOR);
        memberService.saveMember(member);
    }
}
