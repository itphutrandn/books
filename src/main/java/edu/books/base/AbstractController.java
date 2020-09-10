package edu.books.base;

import org.springframework.beans.factory.annotation.Autowired;


import edu.books.domain.User;
import edu.books.security.UserInfoHandler;
import edu.books.service.UserService;

public abstract class AbstractController {

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserInfoHandler userInfoHandler;

	protected User currentUser(String token) {
		return userService.findByToken(token);
	}
}
