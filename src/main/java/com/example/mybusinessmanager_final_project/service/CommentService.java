package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.service.CommentServiceModel;
import com.example.mybusinessmanager_final_project.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    CommentViewModel createComment(CommentServiceModel commentServiceModel);


    List<CommentViewModel> getComments(Long reportId);


}
