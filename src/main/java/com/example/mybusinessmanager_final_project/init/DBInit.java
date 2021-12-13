package com.example.mybusinessmanager_final_project.init;

import com.example.mybusinessmanager_final_project.service.ReportService;
import com.example.mybusinessmanager_final_project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final ReportService reportService;

    public DBInit(UserService userService, ReportService reportService) {
        this.userService = userService;
        this.reportService = reportService;

    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        reportService.initializeReports();
    }
}
