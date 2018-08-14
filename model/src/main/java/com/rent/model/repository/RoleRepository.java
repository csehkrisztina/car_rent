package com.rent.model.repository;

import com.rent.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

    @Query(value = "SELECT role FROM role WHERE role_id IN (SELECT role_id FROM user_role WHERE user_id = :id)", nativeQuery = true)
    String findRoleByUserId(@Param("id") Long userId);
}
