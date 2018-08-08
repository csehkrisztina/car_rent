package com.rent.model.entity;

import com.rent.model.dto.RoleDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String role;

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void update(RoleDto role) {
        this.role = role.getRole();
    }

    public RoleDto toDto() {
        RoleDto role = new RoleDto();
        role.setRole(this.role);
        return role;
    }
}
