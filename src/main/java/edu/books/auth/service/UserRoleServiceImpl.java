package edu.books.auth.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import edu.books.domain.Role;
import edu.books.domain.User;
import edu.books.domain.UserRole;
import edu.books.repository.UserRoleRepository;

import java.util.List;

@Log4j2
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public Page<UserRole> getAllData(Specification<UserRole> spec, Pageable pageable) {
        return userRoleRepository.findAll(spec, pageable);
    }

    @Override
    public List<UserRole> findByAdmin(User user) {
        return userRoleRepository.findByUser(user);
    }

    @Override
    public UserRole findByRole(Role role) {
        return userRoleRepository.findByRole(role);
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void deleteAllByAdmin(int userId) {
        userRoleRepository.deleteAllByUser(userId);
    }
}
