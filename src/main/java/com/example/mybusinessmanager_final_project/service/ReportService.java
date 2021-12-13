package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.binding.ReportAddBindingModel;
import com.example.mybusinessmanager_final_project.model.service.ReportAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.ReportDetailsView;
import com.example.mybusinessmanager_final_project.model.view.ReportSummaryView;

import java.util.List;

public interface ReportService {
    void initializeReports();

    List<ReportSummaryView> getAllReports();

    ReportDetailsView findById(Long id, String currentUser);

    void deleteReport(Long id);

    boolean isOwner(String userName, Long id);

    void editReport(ReportEditServiceModel reportModel);

    ReportAddServiceModel addReport(ReportAddBindingModel reportAddBindingModel, String ownerId);
}
