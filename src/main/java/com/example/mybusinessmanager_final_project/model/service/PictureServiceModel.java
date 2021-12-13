package com.example.mybusinessmanager_final_project.model.service;

public class PictureServiceModel {

    private Long reportId;
    private String title;
    private String url;
    private String publicId;
    private String Author;

    public Long getReportId() {
        return reportId;
    }

    public PictureServiceModel setReportId(Long reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureServiceModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getAuthor() {
        return Author;
    }

    public PictureServiceModel setAuthor(String author) {
        Author = author;
        return this;
    }
}
