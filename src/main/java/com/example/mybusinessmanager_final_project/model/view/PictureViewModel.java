package com.example.mybusinessmanager_final_project.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class PictureViewModel {

    private Long pictureId;
    private String author;
    private Long reportId;
    private Instant created;
    private String title;
    private String url;
    private String publicId;
    private boolean canApprove;
    private boolean canDelete;

    public String getTitle() {
        return title;
    }

    public PictureViewModel setTitle(String title) {
        this.title = title;
        return this;
    }


    public String getUrl() {
        return url;
    }

    public PictureViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public PictureViewModel setPictureId(Long pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PictureViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Long getReportId() {
        return reportId;
    }

    public PictureViewModel setReportId(Long reportId) {
        this.reportId = reportId;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    public PictureViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public PictureViewModel setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public PictureViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
