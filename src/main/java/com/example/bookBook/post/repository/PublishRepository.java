package com.example.bookBook.post.repository;

import com.example.bookBook.post.domain.entity.Publish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishRepository  extends JpaRepository<Publish, Long> {
}
