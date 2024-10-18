package com.example.vuvantrung.repository;

import com.example.vuvantrung.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Tìm sách theo tác giả (Sử dụng @Query với JPQL)
//    @Query("SELECT b FROM Book b WHERE b.author = :author")
    @Query(value = "select * from book where author = :author", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("author") String author);

    // Tìm sách có giá lớn hơn giá trị cụ thể (Sử dụng native SQL)
    @Query(value = "SELECT b FROM Book b WHERE b.price > :price")
    List<Book> findBooksByPriceGreaterThan(@Param("price") Double price);

    // Tìm sách theo tiêu đề và sắp xếp theo giá giảm dần (Sử dụng @Query với JPQL)
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% ")
    List<Book> findBooksByTitle(@Param("title") String title);
}
