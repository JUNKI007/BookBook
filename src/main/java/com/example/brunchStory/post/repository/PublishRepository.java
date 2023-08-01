package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishRepository  extends JpaRepository<Publish, Long> {
}
