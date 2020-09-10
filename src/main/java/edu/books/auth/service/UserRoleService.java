package edu.books.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import edu.books.domain.Role;
import edu.books.domain.User;
import edu.books.domain.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    Page<UserRole> getAllData(Specification<UserRole> spec, Pageable pageable);

    List<UserRole> findByAdmin(User user);

    UserRole findByRole(Role role);

    void save(UserRole userRole);

    void deleteAllByAdmin(int adminId);

}
