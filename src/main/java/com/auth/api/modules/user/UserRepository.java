package com.auth.api.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User getUserByUsername(String username);

    User getUserById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
