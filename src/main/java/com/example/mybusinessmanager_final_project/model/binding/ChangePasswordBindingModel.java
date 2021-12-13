package com.example.mybusinessmanager_final_project.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordBindingModel {

    @NotBlank
    @Size(min = 2, max = 200)
    private String newPassword;

    @NotBlank
    @Size(min = 2, max = 20)
    private String confirmPassword;

    private boolean notMatch = false;

    public ChangePasswordBindingModel() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isNotMatch() {
        return notMatch;
    }

    public void setNotMatch(boolean notMatch) {
        this.notMatch = notMatch;
    }
}
