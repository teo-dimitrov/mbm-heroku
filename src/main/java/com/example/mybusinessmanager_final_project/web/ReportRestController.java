package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.view.ReportSummaryView;
import com.example.mybusinessmanager_final_project.model.view.ReportViewModel;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ReportRestController {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    public ReportRestController(ReportRepository reportRepository,
                               ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("reports/api")
    public ResponseEntity<List<ReportViewModel>> findAll() {

        List<ReportViewModel> reportViewModels
                = reportRepository.
                findAll().
                stream().
                map(re -> {
                    ReportViewModel reportViewModel = modelMapper.map(re, ReportViewModel.class);
                        reportViewModel.setName(re.getName());
                    return reportViewModel;
                }).
                collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(reportViewModels);
    }
}
