package com.example.mybusinessmanager_final_project.config;


import com.example.mybusinessmanager_final_project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private MBMMethodSecurityExpressionHandler mbmMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return mbmMethodSecurityExpressionHandler;
    }

    @Bean
    public MBMMethodSecurityExpressionHandler createExpressionHandler(ReportService reportService) {
        return new MBMMethodSecurityExpressionHandler(reportService);
    }
}
