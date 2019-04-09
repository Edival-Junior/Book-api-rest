package com.edivalsilva.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edivalsilva.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	
	List<Book> findByTitle(String title);
	
}
