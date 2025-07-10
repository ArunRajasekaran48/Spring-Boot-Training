package com.training.demo.repository;
import com.training.demo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(String roleName);
}