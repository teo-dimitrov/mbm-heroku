package com.example.mybusinessmanager_final_project.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class OrderAddBindingModel {

    private Long id;
    @NotNull
    @Size(min = 2, max = 60, message = "name must between 2 and 60 characters!")
    private String name;
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadLine;

    public Long getId() {
        return id;
    }

    public OrderAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderAddBindingModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }
}
