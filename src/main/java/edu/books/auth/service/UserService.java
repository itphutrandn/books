package edu.books.auth.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import edu.books.domain.User;

public interface UserService {

    List<User> findAll();

    Page<User> getAllData(Specification<User> spec, Pageable pageable);

    int getAllDataCount(Specification<User> spec);

    User findByUserName(String name);

    User saveAdmin(User user);

    User findById(int id);

    User save(User user);

    User deleteById(int id);

	User findByEmail(String email);

    void updateToken(String token, String name);

    User updateLastUsed(String name);

    User findByToken(String token);
}
