package com.example.mybusinessmanager_final_project.repository;

import com.example.mybusinessmanager_final_project.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
