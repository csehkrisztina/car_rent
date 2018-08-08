package com.rent.model.dto;

public class UserDto {
    private String firstName;

    private String lastName;

    private String identNumber; // personal identification number = CNP

    private int age;

    private String userName;

    private String password;

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

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

<<<<<<< HEAD
    public boolean equals(Object o){
        if (getClass() == o.getClass()) {
            return true;
        } else {
            return false;
        }
=======
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
>>>>>>> c701ae750eb42e71ac43ea6f227532da86f7e686
    }
}
