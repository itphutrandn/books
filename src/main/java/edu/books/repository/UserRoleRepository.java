package edu.books.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.books.domain.Role;
import edu.books.domain.User;
import edu.books.domain.UserRole;
import edu.books.model.BookAdminEnabledResponse;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

	List<UserRole> findAll();

	Page<UserRole> findAll(Specification<UserRole> spec, Pageable pageable);

	List<UserRole> findByUser(User user);

	UserRole findByRole(Role role);

	@Query(value = "DELETE FROM user_role WHERE user_id = ?", nativeQuery = true)
	void deleteAllByUser(int userId);

}
