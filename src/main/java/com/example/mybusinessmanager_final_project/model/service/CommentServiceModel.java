package com.example.mybusinessmanager_final_project.model.service;

public class CommentServiceModel {

  private Long reportId;
  private String message;
  private String creator;

  public Long getReportId() {
    return reportId;
  }

  public CommentServiceModel setReportId(Long reportId) {
    this.reportId = reportId;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public CommentServiceModel setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getCreator() {
    return creator;
  }

  public CommentServiceModel setCreator(String creator) {
    this.creator = creator;
    return this;
  }
}
