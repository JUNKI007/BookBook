package com.example.brunchStory.post.repository;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
    @Query("SELECT p FROM Post p INNER JOIN FETCH p.author INNER JOIN FETCH p.subject where p.id = :id")
    Optional<Post> findByIdCustom(@Param("id") Long postId);
 //

    @Query("select p from Post p INNER JOIN FETCH p.subject where p.likeCount > 5 and p.localDateTime > :time")
    List<Post> findAllByLike(@Param("time")LocalDateTime localDateTime);
    @Modifying(clearAutomatically = true)
    @Query("delete from Post p where p.id = :id and p.author = :author")
    int deleteCustom(@Param("id")Long postId, @Param("author")Member author);
}
