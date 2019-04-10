package com.edivalsilva.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edivalsilva.spring.model.Book;
import com.edivalsilva.spring.repository.BookRepository;

@CrossOrigin(" * ")
@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/api/book")
	public ResponseEntity<List<Book>> list() {
		List<Book> books = bookRepository.findAll();
		return ResponseEntity.ok().body(books);
	}

	@PostMapping("/api/book")
	public ResponseEntity<String> save(@RequestBody Book book) {
		bookRepository.save(book);
		List<Book> newBook = bookRepository.findByTitle(book.getTitle());

		System.out.println("New book :: " + newBook);

		return ResponseEntity.ok().body("New book id: " + newBook.get(newBook.size() - 1).getId());

	}

	@GetMapping("/api/book/{id}")
	public ResponseEntity<Optional<Book>> findById(@PathVariable("id") long id) {
		Optional<Book> book = bookRepository.findById(id);

		if (!book.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(book);
		}
	}

	@PutMapping("/api/book/{id}")
	public ResponseEntity<Optional<Book>> updateBook(@RequestBody Book book, @PathVariable("id") long id) {
		Optional<Book> oldBook = bookRepository.findById(id);

		if (!oldBook.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		book.setId(id);
		bookRepository.save(book);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/api/book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
		bookRepository.deleteById(id);
		return ResponseEntity.ok().body("Book Deleted");

	}
}
