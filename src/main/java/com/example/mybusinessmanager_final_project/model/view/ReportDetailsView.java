package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import java.time.Instant;

public class ReportDetailsView {

    private Long id;
    private String name;
    private String description;
    private ReportTypeEnum reportTypeEnum;
    private ReportStatusEnum reportStatusEnum;
    private Instant created;
    private Instant modified;
    private boolean canDelete;
    private boolean isOwner;
    private String authorFullName;

    public ReportDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public ReportDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportDetailsView setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportDetailsView setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ReportDetailsView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public ReportDetailsView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ReportDetailsView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public ReportDetailsView setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public ReportDetailsView setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }
}

