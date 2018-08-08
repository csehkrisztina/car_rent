package com.rent.model.repository;

import com.rent.model.entity.RoleEntity;
import com.rent.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(String role);

    // SELECT r.role FROM role AS r WHERE id IN (SELECT ur.role_id FROM user_role AS ur WHERE ur.user_id = :uid)

    @Query("SELECT role FROM RoleEntity WHERE id IN (SELECT roleId FROM UserRoleEntity WHERE userId = :uid)")
    List<String> findRolesByUserId(@Param("uid") Long userId);
}
