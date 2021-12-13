package com.example.mybusinessmanager_final_project.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangeUserAccountSettingsBindingModel {
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


    public String getFirstName() {
        return firstName;
    }

    public ChangeUserAccountSettingsBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ChangeUserAccountSettingsBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ChangeUserAccountSettingsBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChangeUserAccountSettingsBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
