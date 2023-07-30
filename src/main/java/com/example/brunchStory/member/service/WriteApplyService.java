package com.example.brunchStory.member.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.repository.WriteApplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class WriteApplyService {
    private final WriteApplyRepository writeApplyRepository;

    // 작가 신청 정보를 저장
    public void saveWriteApply(String title, String content, LocalDate localDate, Member applicant){
        WriterApply writerApply = new WriterApply();
        writerApply.setTitle(title);
        writerApply.setContent(content);
        writerApply.setLocalDate(localDate);
        writerApply.setApplicant(applicant);
        writeApplyRepository.save(writerApply);
    }
    // 작가 신청 정보를 조회(Id로 조회)
    public WriterApply getWriterApplyById(Long id){
        return writeApplyRepository.findById(id).orElse(null);
    }

    //작가 신청 정보를 조회(작가 신청자로 조회)
    public List<WriterApply> getWriterAppliesByApplicant(Member applicant){
        return writeApplyRepository.findByApplicant(applicant);
    }

    //모든 작가 신청 정보를 조회
    public List<WriterApply> getAllWriterApplies(){
        return writeApplyRepository.findAll();
    }

    //작가 신청 정보를 삭제
    public void deleteWriterApply(Long id){
        writeApplyRepository.deleteById(id);
    }
}
