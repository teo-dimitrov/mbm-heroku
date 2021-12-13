package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

public class ReportSummaryView {

    private long id;
    private String name;
    private String description;
    private ReportTypeEnum reportTypeEnum;
    private ReportStatusEnum reportStatusEnum;

    public long getId() {
        return id;
    }

    public ReportSummaryView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportSummaryView setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportSummaryView setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }
}
