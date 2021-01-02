package org.sid.authservice.repositories;

import org.sid.authservice.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRole(String role);
}
