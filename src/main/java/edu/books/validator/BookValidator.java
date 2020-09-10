package edu.books.validator;

import static org.springframework.util.Assert.isTrue;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.books.domain.Book;
import edu.books.form.BookForm;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component("bookValidator")
public class BookValidator extends AbstractThrowableValidator<BookForm, IllegalArgumentException> {

	@Autowired
	@Qualifier("existingValidator")
	private ExistingValidator existingValidator;
	
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	@Override
	public void validate(BookForm book) throws IllegalArgumentException {
		existingValidator.validateNullOrEmpty(book.getTitle(), " Title");
		existingValidator.validateNullOrEmpty(book.getAuthor(), " Author");
		existingValidator.validateNullOrEmpty(book.getDescription(), " Description");
		existingValidator.validateNullOrEmpty(book.getEnabled(), " Enabled");
	}

	public void validateUpdate(Book book) throws IllegalArgumentException {
		existingValidator.validateNullOrEmpty(book.getId(), " Id");
		isTrue(isNumeric(String.valueOf(book.getId())), "Id must integer");
		existingValidator.validateNullOrEmpty(book.getTitle(), " Title");
		existingValidator.validateNullOrEmpty(book.getAuthor(), " Author");
		existingValidator.validateNullOrEmpty(book.getDescription(), " Description");
	}
	
	 
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
}
