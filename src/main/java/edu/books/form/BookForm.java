package edu.books.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class BookForm {
	
	private Integer id;
	
	private String title;

	private String author;

	private String description;

	private String enabled;
	
	private String delImg;

	private MultipartFile avatar;
	
}