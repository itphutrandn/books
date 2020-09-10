package edu.books.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.books.domain.Book;
import edu.books.model.BookAdminResponse;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>,//
        JpaSpecificationExecutor<Book> {


    @Query(value =
            "SELECT new edu.books.model.BookAdminResponse(b.id, b.title, b.author) "+
            "FROM Book b " +
            "WHERE b.enabled = '1' " +
            "AND (:title IS NULL OR b.title LIKE %:title% ) " +
            "AND (:author IS NULL OR b.author LIKE %:author% ) ")
    Page<BookAdminResponse> getSearchAllBook(@Param("title") String title,
                                             @Param("author") String author,
                                             Pageable pageable);
    
    
    @Query(value =
            "SELECT new edu.books.model.BookAdminResponse(b.id, b.title, b.author) "+
            "FROM Book b INNER JOIN BookUser bu ON bu.book.id = b.id INNER JOIN User u ON u.id = bu.user.id " +
            "WHERE b.enabled = '1' AND u.id = :userId " +
            "AND (:title IS NULL OR b.title LIKE %:title% ) " +
            "AND (:author IS NULL OR b.author LIKE %:author% ) ")
    Page<BookAdminResponse> getSearchAllBook(@Param("title") String title,
                                             @Param("author") String author,
                                             @Param("userId") int userId,
                                             Pageable pageable);
    
    Book findById(int id);
    
    @Query(value = "DELETE FROM book_user WHERE book_id = ? AND user_id = ?", nativeQuery = true)
    void delete(int bookId, int userId);
}
