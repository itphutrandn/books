package edu.books.service;

import edu.books.domain.User;

public interface UserService {

	User findById(int id);

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	void updateToken(String token, String email);
	
	User findByToken(String token);
}
