package com.example.bookBook.post.repository;

import com.example.bookBook.post.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
}
