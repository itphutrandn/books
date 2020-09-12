package edu.books.controller.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("error-book")
	public String error() {
		return "error/error";
	}
}
