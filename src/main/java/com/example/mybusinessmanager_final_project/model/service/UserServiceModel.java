package com.example.mybusinessmanager_final_project.model.service;

import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;

import java.util.List;

public class UserServiceModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
    private List<String> roles;
    private UserRoleEnum userRoleEnum;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserServiceModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserServiceModel setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }
}
