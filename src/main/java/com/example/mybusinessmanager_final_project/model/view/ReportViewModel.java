package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;


public class ReportViewModel {

    private Long id;
    private String name;
    private ReportTypeEnum reportTypeEnum;
    private ReportStatusEnum reportStatusEnum;


    public ReportViewModel() {

    }

    public Long getId() {
        return id;
    }

    public ReportViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportViewModel setName(String name) {
        this.name = name;
        return this;
    }


    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportViewModel setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportViewModel setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }

}
