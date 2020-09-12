package edu.books.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.books.auth.service.UserService;
import edu.books.domain.Role;
import edu.books.domain.User;

@Component
public class UserInfoHandler {
	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	public edu.books.domain.User getUser(User user) {
		User userInfo = null;
		if (user != null) {
			userInfo = userService.findByEmail(user.getEmail());
		}
		return userInfo;
	}

	public User getInfoUser() {
		User userInfo = null;
		if (request.getAttribute("userInfo") != null) {
			userInfo = (User) request.getAttribute("userInfo");
		}
		return userInfo;
	}

	public List<Role> getRoles(HttpSession session) {
		List<Role> roles = new ArrayList<>();
		if (session.getAttribute("roles") != null) {
			roles = (List<Role>) session.getAttribute("roles");
		}
		return roles;
	}

	public boolean isAdmin(HttpSession session) {
		for (Role r : getRoles(session)) {
			if ("ROLE_ADMIN".equals(r.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean isUser(HttpSession session) {
		for (Role r : getRoles(session)) {
			if ("ROLE_USER".equals(r.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean isGuest(HttpSession session) {
		for (Role r : getRoles(session)) {
			if ("ROLE_GUEST".equals(r.getName())) {
				return true;
			}
		}
		return false;
	}
}
