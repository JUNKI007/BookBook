package com.example.brunchStory.post.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Publish;
import com.example.brunchStory.post.domain.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
//    Query("select s from Subject s where s.id In :subjectIds")@Query("select s from Subject s where s.id In :subjectIds")
    List<Subject> findAllByIdIn(List<Long> subjectIds);
}
