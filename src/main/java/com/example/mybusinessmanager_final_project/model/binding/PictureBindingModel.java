package com.example.mybusinessmanager_final_project.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class PictureBindingModel {

    private String id;
    private String title;
    private MultipartFile picture;
    private String report;
    private String author;


    public String getPictureId() {
        return id;
    }

    public PictureBindingModel setPictureId(String id) {
        this.id = id;
        return this;
    }

    public String getReport() {
        return report;
    }

    public PictureBindingModel setReport(String report) {
        this.report = report;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PictureBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }



    public String getTitle() {
        return title;
    }

    public PictureBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
