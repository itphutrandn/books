package edu.books.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.books.auth.service.UserService;
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

    public List<String> getRoles(HttpSession session) {
        List<String> roles = new ArrayList<>();
        if (session.getAttribute("roles") != null) {
            roles = (List<String>) session.getAttribute("roles");
        }
        return roles;
    }
}
