package com.example.mybusinessmanager_final_project.model.binding;

import javax.validation.constraints.NotBlank;

public class NewCommentBindingModel {

  @NotBlank
  private String message;

  public String getMessage() {
    return message;
  }

  public NewCommentBindingModel setMessage(String message) {
    this.message = message;
    return this;
  }
}
