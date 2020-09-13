package edu.books.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.books.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, //
		JpaSpecificationExecutor<User> {

	List<User> findAll();

	Page<User> findAll(Specification<User> spec, Pageable pageable);

	List<User> findAll(Specification<User> spec);

	User findById(int id);

	@Query(value = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP() WHERE Id = ?", nativeQuery = true)
    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Transactional
	User updateDeleteAt(int id);
	
	User findByEmail(String email);

	@Query(value = "UPDATE user SET token = ? WHERE email = ?", nativeQuery = true)
    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Transactional
	void updateToken(String token, String email);

	@Query(value = "UPDATE user SET last_used = CURRENT_TIMESTAMP() WHERE name = ?", nativeQuery = true)
    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Transactional
	User updateLastUsed(String name);

	User findByToken(String token);

	@Query(nativeQuery = true, value = "SELECT * from user where email = ? AND password = ?")
	User findByEmailAndPassword(String email, String password);
}
