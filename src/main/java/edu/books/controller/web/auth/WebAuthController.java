package edu.books.controller.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class WebAuthController {

	@GetMapping("login")
	public String login() {
		return "auth/login";
	}
}
