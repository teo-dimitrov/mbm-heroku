package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.validator.UniqueUserName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    @NotBlank
    @UniqueUserName(message = "username already exist")
    @Size(min = 3, max = 20, message = "Username must between 3 and 20 characters")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "First Name must between 3 and 20 characters")
    private String firstName;
    @NotNull
    @Size(min = 3, max = 20, message = "Last Name must between 3 and 20 characters")
    private String lastName;
    @NotNull
    @Size(min = 3, max = 20, message = "Email must between 3 and 20 characters")
    private String email;
    @NotNull
    @Size(min = 3, max = 20, message = "Password must between 3 and 20 characters")
    private String password;
    @NotNull
    @Size(min = 3, max = 20, message = "Confirm Password must between 3 and 20 characters")
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
