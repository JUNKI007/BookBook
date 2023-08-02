package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
