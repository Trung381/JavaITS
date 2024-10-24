package com.example.vuvantrung.controller;

import com.example.vuvantrung.entity.Book;
import com.example.vuvantrung.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/get-all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("add")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.getBookById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            Book updatedBook = bookService.saveBook(book);
            return ResponseEntity.ok(updatedBook);
        }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return bookService.getBookById(id).map(book -> {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/price-greater-than")
    public List<Book> getBooksByPriceGreaterThan(@RequestParam Double price) {
        return bookService.getBooksByPriceGreaterThan(price);
    }

    @GetMapping("/search")
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }
}

