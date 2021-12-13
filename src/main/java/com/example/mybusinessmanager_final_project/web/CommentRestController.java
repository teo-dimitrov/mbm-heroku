package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.NewCommentBindingModel;
import com.example.mybusinessmanager_final_project.model.service.CommentServiceModel;
import com.example.mybusinessmanager_final_project.model.validator.ApiError;
import com.example.mybusinessmanager_final_project.model.view.CommentViewModel;
import com.example.mybusinessmanager_final_project.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

  private final CommentService commentService;
  private final ModelMapper modelMapper;

  public CommentRestController(CommentService commentService,
                               ModelMapper modelMapper) {
    this.commentService = commentService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/api/{reportId}/comments")
  public ResponseEntity<List<CommentViewModel>> getComments(
      @PathVariable Long reportId,
      Principal principal
  ) {
    return ResponseEntity.ok(
        commentService.getComments(reportId));
  }

  @PostMapping("/api/{reportId}/comments")
  public ResponseEntity<CommentViewModel> newComment(
      @AuthenticationPrincipal UserDetails principal,
      @PathVariable Long reportId,
      @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
  ) {
    CommentServiceModel serviceModel =
        modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
    serviceModel.setCreator(principal.getUsername());
    serviceModel.setReportId(reportId);

    CommentViewModel newComment =
        commentService.createComment(serviceModel);

    URI locationOfNewComment =
        URI.create(String.format("/api/%s/comments/%s", reportId, newComment.getCommentId()));

    return ResponseEntity.
        created(locationOfNewComment).
        body(newComment);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    exc.getFieldErrors().forEach(fe ->
        apiError.addFieldWithError(fe.getField()));

    return ResponseEntity.badRequest().body(apiError);
  }
}
