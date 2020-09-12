package edu.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAdminResponse {

    private Integer id;
    
    private String title;
    
    private String author;
    
    private String enabled;

	public BookAdminResponse(Integer id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
}
