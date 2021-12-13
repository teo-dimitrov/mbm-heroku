package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;

public class UserViewModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private String roles;
    private UserRoleEnum userRoleEnum;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public UserViewModel setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserViewModel setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }
}
