package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@Validated @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.addBook(book));
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@Validated @RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.addAuthor(author));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Validated @RequestBody Book updatedBook) {
        return ResponseEntity.ok(libraryService.updateBook(id, updatedBook));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(libraryService.getAllAuthors());
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(libraryService.searchBooksByTitle(title));
    }

    @GetMapping("/authors/search")
    public ResponseEntity<List<Author>> searchAuthorsByName(@RequestParam String name) {
        return ResponseEntity.ok(libraryService.searchAuthorsByName(name));
    }
}
