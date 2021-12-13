package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.binding.ReportAddBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.ReportEntity;
import com.example.mybusinessmanager_final_project.model.entity.UserEntity;
import com.example.mybusinessmanager_final_project.model.entity.UserRoleEntity;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;
import com.example.mybusinessmanager_final_project.model.service.ReportAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.ReportDetailsView;
import com.example.mybusinessmanager_final_project.model.view.ReportSummaryView;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.ReportService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void initializeReports() {
        if (reportRepository.count() == 0) {
            ReportEntity report1 = new ReportEntity();
            report1.setName("Report 1");
            report1.setDescription("Report 1 Descr...");
            report1.setReportTypeEnum(ReportTypeEnum.REPORT);
            report1.setReportStatusEnum(ReportStatusEnum.UNCHECKED);
            report1.setAuthor(userRepository.findByUsername("admin").orElse(null));

            ReportEntity report2 = new ReportEntity();
            report2.setName("Report 2");
            report2.setDescription("Report 2 Descr...");
            report2.setReportTypeEnum(ReportTypeEnum.OFFER);
            report2.setReportStatusEnum(ReportStatusEnum.CHECKED);
            report2.setAuthor(userRepository.findByUsername("member").orElse(null));

            reportRepository.saveAll(List.of(report1, report2));
        }
    }

    @Override
    public List<ReportSummaryView> getAllReports() {
        return reportRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public ReportDetailsView findById(Long id, String currentUser) {
        return this.reportRepository
                .findById(id).map(r -> mapDetailsView(currentUser, r)).get();
    }

    @Override
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {

        Optional<ReportEntity> reportOptional = reportRepository.findById(id);
        Optional<UserEntity> caller = userRepository.findByUsername(userName);

        if (reportOptional.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            ReportEntity reportEntity = reportOptional.get();
            return isAdmin(caller.get()) || reportEntity.getAuthor().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public void editReport(ReportEditServiceModel reportModel) {

        ReportEntity reportEntity = reportRepository
                .findById(reportModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Report with id " + reportModel.getId() + " not found!"));

        reportEntity
                .setName(reportModel.getName())
                .setDescription(reportModel.getDescription())
                .setReportStatusEnum(reportModel.getReportStatusEnum())
                .setReportTypeEnum(reportModel.getReportTypeEnum());

        reportRepository.save(reportEntity);

    }

    @Override
    public ReportAddServiceModel addReport(ReportAddBindingModel reportAddBindingModel, String ownerId) {
        UserEntity userEntity = userRepository.findByUsername(ownerId).orElseThrow();
        ReportAddServiceModel reportAddServiceModel = modelMapper.map(reportAddBindingModel, ReportAddServiceModel.class);
        ReportEntity newReport = modelMapper.map(reportAddServiceModel, ReportEntity.class);
        newReport.setAuthor(userEntity);

        ReportEntity savedReport = reportRepository.save(newReport);

        return modelMapper.map(savedReport, ReportAddServiceModel.class);
    }

    private ReportSummaryView map(ReportEntity reportEntity) {
        return this.modelMapper.map(reportEntity, ReportSummaryView.class);
    }


    private ReportDetailsView mapDetailsView(String currentUser, ReportEntity report) {

        ReportDetailsView reportDetailsView = this.modelMapper.map(report, ReportDetailsView.class);

        reportDetailsView.setCanDelete(isOwner(currentUser, report.getId()));
        reportDetailsView.setOwner(isOwner(currentUser, report.getId()));
        reportDetailsView.setAuthorFullName(report.getAuthor().getFirstName() + ' ' + report.getAuthor().getLastName());

        return reportDetailsView;
    }
}
