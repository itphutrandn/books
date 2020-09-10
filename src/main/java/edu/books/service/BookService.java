package edu.books.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import edu.books.domain.Book;
import edu.books.model.BookAdminResponse;

public interface BookService {

	Page<BookAdminResponse> getSearchAllBook(@Param("title") String address, @Param("author") String author,
			Pageable pageable);

	Page<BookAdminResponse> getSearchAllBook(@Param("title") String title, @Param("author") String author,
			@Param("userId") int userId, Pageable pageable);

	Book findById(int id);

	void save(Book book);

	void delete(Book book);
	
	void delete(int id);
	
	void delete(int bookId, int userId);
}
