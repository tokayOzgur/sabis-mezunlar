
package com.sabis.ws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sabis.ws.model.User;

/**
 * @author tokay
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByActivationToken(String token);

	Optional<User> findByPasswordResetToken(String token);

	Page<User> findAllByActiveAndIsDeletedAndIdNot(boolean active, boolean isDeleted, Pageable pageable, int id);

	List<User> findByIdIn(List<Integer> userIds);

}
