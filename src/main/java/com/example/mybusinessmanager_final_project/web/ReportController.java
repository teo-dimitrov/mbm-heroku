package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.ReportAddBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.ReportEditBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.example.mybusinessmanager_final_project.model.service.ReportAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.ReportDetailsView;
import com.example.mybusinessmanager_final_project.service.ReportService;
import com.example.mybusinessmanager_final_project.service.impl.MBMUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ReportController {
    private final ReportService reportService;
    private final ModelMapper modelMapper;

    public ReportController(ReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reports/all")
    public String allReports(Model model) {
        model.addAttribute("reports",
                reportService.getAllReports());
        return "reports-all";
    }

    @GetMapping("/reports/{id}/report-details")
    public String showReport(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("report", this.reportService.findById(id, principal.getName()));
        return "report-details";
    }

    @DeleteMapping("/reports/{id}")
    public String deleteReport(@PathVariable Long id,
                              Principal principal) {

       reportService.deleteReport(id);

        return "redirect:/reports/all";
    }

    @GetMapping("/reports/{id}/edit")
    public String editReport(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal MBMUser currentUser) {

        ReportDetailsView reportDetailsView = reportService.findById(id, currentUser.getUserIdentifier());
        ReportEditBindingModel reportEditBindingModel = modelMapper.map(
                reportDetailsView,
                ReportEditBindingModel.class
        );
        model.addAttribute("type", ReportTypeEnum.values());
        model.addAttribute("status", ReportStatusEnum.values());
        model.addAttribute("reportModel", reportEditBindingModel);

        return "report-edit";
    }

    @GetMapping("/reports/{id}/edit/error")
    public String editReportErrors(@PathVariable Long id, Model model) {
        model.addAttribute("type", ReportTypeEnum.values());
        model.addAttribute("status", ReportTypeEnum.values());

        return "report-edit";
    }

    @PatchMapping("/reports/{id}/edit")
    public String editReport(
            @PathVariable Long id,
            @Valid ReportEditBindingModel reportModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("reportModel", reportModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reportModel", bindingResult);

            return "redirect:/reports/" + id + "/edit/error";
        }

        ReportEditServiceModel serviceModel = modelMapper.map(reportModel,
                ReportEditServiceModel.class);
        serviceModel.setId(id);

        reportService.editReport(serviceModel);

        return "redirect:/reports/" + id + "/report-details";
    }

    @GetMapping("/reports/add")
    public String getAddReportPage(Model model) {

        if (!model.containsAttribute("reportAddBindingModel")) {
            model.
                    addAttribute("reportAddBindingModel", new ReportAddBindingModel());
        }
        return "reports-add";
    }

    @PostMapping("/reports/add")
    public String addReport(@Valid ReportAddBindingModel reportAddBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal MBMUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reportAddBindingModel", reportAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reportAddBindingModel", bindingResult);

            return "redirect:add";
        }
        ReportAddServiceModel savedReportAddServiceModel = reportService.addReport(reportAddBindingModel, user.getUserIdentifier());

        return "redirect:/reports/" + savedReportAddServiceModel.getId() + "/report-details";
    }
}
