package edu.books.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import edu.books.domain.Book;
import edu.books.model.BookAdminEnabledResponse;
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
	
	List<BookAdminResponse> getAllByUserId(Integer userId);
	
	List<BookAdminEnabledResponse> getListEnabled();
	
	int active(String enable, Integer id);
}
