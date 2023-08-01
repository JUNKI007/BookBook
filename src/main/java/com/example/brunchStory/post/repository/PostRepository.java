package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
    @Query("SELECT p FROM Post p INNER JOIN FETCH p.author INNER JOIN FETCH p.subject where p.id = :id")
    Optional<Post> findByIdCustom(@Param("id") Long postId);
 //
}
