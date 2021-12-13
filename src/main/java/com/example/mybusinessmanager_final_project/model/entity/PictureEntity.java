package com.example.mybusinessmanager_final_project.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String publicId;
    private String title;
    private String url;
    private boolean approved;
    @ManyToOne
    private ReportEntity reportEntity;
    @ManyToOne
    private UserEntity author;


    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }


    public boolean isApproved() {
        return approved;
    }

    public PictureEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public ReportEntity getReportEntity() {
        return reportEntity;
    }

    public PictureEntity setReportEntity(ReportEntity reportEntity) {
        this.reportEntity = reportEntity;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PictureEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }


}
