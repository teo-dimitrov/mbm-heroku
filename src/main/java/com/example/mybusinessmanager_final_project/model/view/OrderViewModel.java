package com.example.mybusinessmanager_final_project.model.view;


import java.time.Instant;
import java.time.LocalDateTime;

public class OrderViewModel {
    private Long id;
    private String name;
    private Instant created;
    private LocalDateTime deadLine;

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderViewModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }
}
