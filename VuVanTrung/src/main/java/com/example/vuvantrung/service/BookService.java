package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Book;
import com.example.vuvantrung.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() { return bookRepository.findAll();}

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        log.info("Book has title {} is added in database", book.getTitle());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        log.info("Book has id {} is added in database", id);
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> getBooksByPriceGreaterThan(Double price) {
        return bookRepository.findBooksByPriceGreaterThan(price);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }
}
