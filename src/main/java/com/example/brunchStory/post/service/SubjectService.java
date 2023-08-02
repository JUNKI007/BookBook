package com.example.brunchStory.post.service;

import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject findById(Long SubjectId){
        Optional<Subject> byId = subjectRepository.findById(SubjectId);
        Subject subject = byId.orElseThrow(() -> new RuntimeException());
        return subject;
    }

    public List<Subject> findAllByIds(List<Long> subjectIds){
        List<Subject> allById = subjectRepository.findAllByIdIn(subjectIds);

        return allById;
    }
}
