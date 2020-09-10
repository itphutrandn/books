package edu.books.auth.service;

import java.util.List;

import edu.books.domain.Role;

public interface RoleService {

    List<Role> findAll();

    Role findById(int id);

    Role findByName(String name);
}
