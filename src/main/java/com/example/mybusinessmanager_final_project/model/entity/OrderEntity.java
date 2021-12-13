package com.example.mybusinessmanager_final_project.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column
    private LocalDateTime deadLine;
    @Column
    @Lob
    private String description;
    @ManyToOne
    private UserEntity author;

    public String getName() {
        return name;
    }

    public OrderEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderEntity setDeadLine(LocalDateTime deadline) {
        this.deadLine = deadline;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public OrderEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
