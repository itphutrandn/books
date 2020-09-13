package edu.books.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.books.domain.User;
import edu.books.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("userService")
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllData(Specification<User> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public int getAllDataCount(Specification<User> spec) {
        return userRepository.findAll(spec).size();
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public User saveAdmin(User admin) {
        return userRepository.save(admin);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User deleteById(int id) {
        return userRepository.updateDeleteAt(id);
    }

    @Override
    public User save(User user) {
    	log.debug("save called");
    	System.out.println("user.getPassword(): "+user.getPassword());
      //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateToken(String token, String name) {
         userRepository.updateToken(token, name);
    }

    @Override
    public User updateLastUsed(String name) {
        return userRepository.updateLastUsed(name);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
