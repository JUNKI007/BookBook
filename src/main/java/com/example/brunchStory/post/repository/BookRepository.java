package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
}
