package com.rent.model.entity;

import com.rent.model.dto.RoleDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
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
