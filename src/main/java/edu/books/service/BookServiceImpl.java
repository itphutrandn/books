package edu.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.books.domain.Book;
import edu.books.model.BookAdminEnabledResponse;
import edu.books.model.BookAdminResponse;
import edu.books.repository.BookRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<BookAdminResponse> getSearchAllBook(String title, String author, Pageable pageable) {
		return bookRepository.getSearchAllBook(title, author, pageable);
	}

	@Override
	public Page<BookAdminResponse> getSearchAllBook(String title, String author, int userId, Pageable pageable) {
		return bookRepository.getSearchAllBook(title, author, userId, pageable);
	}

	@Override
	public Book findById(int id) {
		return bookRepository.findById(id);
	}

	@Override
	@Transactional
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	@Transactional
	public void delete(Book book) {
		bookRepository.delete(book);
	}

	@Override
	@Transactional
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public void delete(int bookId, int userId) {
		bookRepository.delete(bookId, userId);
	}

	@Override
	public List<BookAdminResponse> getAllByUserId(Integer userId) {
		return bookRepository.getSearchAllBook(userId);
	}

	@Override
	public int active(String enable, Integer id) {
		return bookRepository.active(enable, id);
	}

	@Override
	public List<BookAdminEnabledResponse> getListEnabled() {
		return bookRepository.getListEnabled();
	}

	
}
