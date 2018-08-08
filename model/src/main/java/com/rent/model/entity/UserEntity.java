package com.rent.model.entity;

import com.rent.model.dto.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    // personal identification number = CNP
    private String identNumber;

    private int age;

    private String userName;

    private String password;

    private boolean enabled;

//    @ManyToOne()
//    private RoleEntity role;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
    }

    public int getAge() {
        return age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public RoleEntity getRole() {
//        return role;
//    }
//
//    public void setRole(RoleEntity role) {
//        this.role = role;
//    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void update(UserDto user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.identNumber = user.getIdentNumber();
        this.age = user.getAge();
        this.userName = user.getUserName();
    }

    public UserDto toDto() {
        UserDto user = new UserDto();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setIdentNumber(this.identNumber);
        user.setAge(this.age);
        user.setUserName(this.userName);
        return user;
    }
}
