package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReportEditBindingModel {
    private Long id;
    @NotNull
    @Size(min = 3, max = 40, message = "Name must be between 3 and 20 characters")
    private String name;
    @NotNull
    @NotBlank(message = "Description can not be empty!")
    private String description;
    @NotNull
    private ReportTypeEnum reportTypeEnum;
    @NotNull
    private ReportStatusEnum reportStatusEnum;

    public Long getId() {
        return id;
    }

    public ReportEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportEditBindingModel setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportEditBindingModel setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }
}
