package com.example.brunchStory.post.repository;

import com.example.brunchStory.post.domain.entity.Book;
import com.example.brunchStory.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{

    @Query("SELECT b FROM Book b INNER JOIN b.author INNER JOIN b.publishedPosts where b.id = :id")
    public Optional<Book> findByIdCustom(@Param("id") Long bookId);
}
