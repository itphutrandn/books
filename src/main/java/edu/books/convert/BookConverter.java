package edu.books.convert;

import org.springframework.stereotype.Component;

import edu.books.domain.Book;
import edu.books.form.BookForm;

@Component("bookConverter")
public class BookConverter extends AbstractThrowableConverter<BookForm, Book, IllegalArgumentException> {

	@Override
	public Book convert(BookForm from) throws IllegalArgumentException {
		Book to = null;
		if (from != null) {
			to = new Book();
			to.setAuthor(from.getAuthor());
			to.setDescription(from.getDescription());
			to.setEnabled(from.getEnabled());
			to.setTitle(from.getTitle());
		}
		return to;
	}

}
