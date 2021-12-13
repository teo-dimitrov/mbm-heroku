package com.example.mybusinessmanager_final_project.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class CommentViewModel {

  private Long commentId;
  private String message;
  private String user;
  private Instant created;
  private boolean canApprove;
  private boolean canDelete;

  public Long getCommentId() {
    return commentId;
  }

  public CommentViewModel setCommentId(Long commentId) {
    this.commentId = commentId;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public CommentViewModel setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getUser() {
    return user;
  }

  public CommentViewModel setUser(String user) {
    this.user = user;
    return this;
  }

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
  public Instant getCreated() {
    return created;
  }

  public CommentViewModel setCreated(Instant created) {
    this.created = created;
    return this;
  }

  public boolean isCanApprove() {
    return canApprove;
  }

  public CommentViewModel setCanApprove(boolean canApprove) {
    this.canApprove = canApprove;
    return this;
  }

  public boolean isCanDelete() {
    return canDelete;
  }

  public CommentViewModel setCanDelete(boolean canDelete) {
    this.canDelete = canDelete;
    return this;
  }
}
