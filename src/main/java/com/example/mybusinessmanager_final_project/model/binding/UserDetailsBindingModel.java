package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;

public class UserDetailsBindingModel {

    private Long id;
    private String isActive;
    private UserRoleEnum role;

    public UserDetailsBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public UserDetailsBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserDetailsBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public UserDetailsBindingModel setIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public UserRoleEnum getRoles() {
        return role;
    }

    public UserDetailsBindingModel setRoles(UserRoleEnum roles) {
        this.role = roles;
        return this;
    }
}
