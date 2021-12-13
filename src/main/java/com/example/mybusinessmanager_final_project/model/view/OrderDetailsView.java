package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import java.time.Instant;
import java.time.LocalDateTime;

public class OrderDetailsView {

    private Long id;
    private String name;
    private String description;
    private Instant created;
    private LocalDateTime deadLine;
    private boolean canDelete;
    private boolean isOwner;

    public OrderDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public OrderDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderDetailsView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderDetailsView setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public OrderDetailsView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public OrderDetailsView setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }
}
