package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.entity.CommentEntity;
import com.example.mybusinessmanager_final_project.model.service.CommentServiceModel;
import com.example.mybusinessmanager_final_project.model.view.CommentViewModel;
import com.example.mybusinessmanager_final_project.repository.CommentRepository;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.CommentService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(ReportRepository reportRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        Objects.requireNonNull(commentServiceModel.getCreator());

        var report = reportRepository.
                findById(commentServiceModel.getReportId()).
                orElseThrow(() -> new ObjectNotFoundException("report with id " + commentServiceModel.getReportId() + " not found!"));

        var author = userRepository.
                findByUsername(commentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with username " + commentServiceModel.getCreator() + " not found!"));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(Instant.now());
        newComment.setReportEntity(report);
        newComment.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long reportId) {
        var reportOpt = reportRepository.
                findById(reportId);

        if (reportOpt.isEmpty()) {
            throw new ObjectNotFoundException("Report with id " + reportId + " was not found!");
        }

        return reportOpt.
                get().
                getComments().
                stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
