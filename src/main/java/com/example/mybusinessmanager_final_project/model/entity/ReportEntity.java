package com.example.mybusinessmanager_final_project.model.entity;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reports")
public class ReportEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Lob
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportTypeEnum reportTypeEnum;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportStatusEnum reportStatusEnum;
    @ManyToOne
    private UserEntity author;
    @OneToMany(mappedBy = "reportEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "reportEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PictureEntity> pictures;


    public String getName() {
        return name;
    }

    public ReportEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportEntity setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportEntity setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ReportEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public ReportEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ReportEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
