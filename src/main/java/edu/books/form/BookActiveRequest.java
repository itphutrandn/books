package edu.books.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookActiveRequest {
	private Integer bookId;

	private String enabled;

}
