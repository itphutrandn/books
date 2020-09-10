package edu.books.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAdminRequest {
	private String title;

	private String author;

	private Integer pageIndex = 1;

	private Integer pageRecord = 10;

	private String sortBy = "id";

	private String orderBy = "DESC";
	
	private Integer userId;

}
