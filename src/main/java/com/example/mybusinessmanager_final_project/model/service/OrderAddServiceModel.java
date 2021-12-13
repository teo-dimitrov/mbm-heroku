package com.example.mybusinessmanager_final_project.model.service;

import java.time.Instant;
import java.time.LocalDateTime;

public class OrderAddServiceModel {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadLine;

    public Long getId() {
        return id;
    }

    public OrderAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderAddServiceModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }
}
