package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Publish;
import com.example.brunchStory.post.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
