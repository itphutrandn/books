package edu.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.books.domain.User;
import edu.books.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		if (findByEmail(email) != null) {
			if (bCryptPasswordEncoder.matches(password, findByEmail(email).getPassword())) {
				password = findByEmail(email).getPassword();
			}
		}
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User updateToken(String token, String name) {
		return userRepository.updateToken(token, name);
	}

	@Override
	public User findByToken(String token) {
		return userRepository.findByToken(token);
	}

}
