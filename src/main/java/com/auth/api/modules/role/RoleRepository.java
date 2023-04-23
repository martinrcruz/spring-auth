package com.auth.api.modules.role;

import com.auth.api.util.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);

    Set<Role> getRoleByName(UserRole name);

}
