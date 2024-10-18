package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Book;
import com.example.vuvantrung.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Lấy danh sách tất cả sách
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Lấy sách theo ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Thêm mới hoặc cập nhật sách
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Xóa sách theo ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Tìm sách theo tác giả
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    // Tìm sách có giá lớn hơn một giá trị nhất định
    public List<Book> getBooksByPriceGreaterThan(Double price) {
        return bookRepository.findBooksByPriceGreaterThan(price);
    }

    // Tìm sách theo tiêu đề và sắp xếp theo giá giảm dần
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }
}
