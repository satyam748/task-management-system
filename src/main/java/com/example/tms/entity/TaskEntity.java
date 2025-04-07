package com.example.tms.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String status;

    private Timestamp createdAt;

    @ManyToOne()
    @JoinColumn(name = "assigned_user_id")
    private UserEntity assignedUser;

    public UserEntity getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserEntity assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted = false;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

