package com.rent.model.entity;

import com.rent.model.dto.RoleDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends Base {

    private String role;
    @OneToMany
    private List<Users> users;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void update(RoleDto role) {
        this.role = role.getRole();
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public RoleDto toDto() {
        RoleDto role = new RoleDto();
        role.setRole(this.role);
        return role;
    }
}
