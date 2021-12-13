package com.example.mybusinessmanager_final_project.model.view;

public class UserDetailsView {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public UserDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsView setEmail(String email) {
        this.email = email;
        return this;
    }
}
