package edu.books.controller.web.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPublicBookController {

	@GetMapping({"","news"})
	public String index() {
		return "news/news";
	}

}
