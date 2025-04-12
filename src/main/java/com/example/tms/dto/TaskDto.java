package com.example.tms.dto;


import java.util.List;

public class TaskDto {

    private int id;
    private String title;
    private String description;
    private String status;
    private Integer assignedUser;
    private List<String> attachedFile;

    public TaskDto(int id, String title, String description, String status, Integer assignedUser,  List<String> attachedFile) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedUser = assignedUser;
        this.attachedFile = attachedFile;
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

    public Integer getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(Integer assignedUser) {
        this.assignedUser = assignedUser;
    }

    public  List<String> getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile( List<String> attachedFile) {
        this.attachedFile = attachedFile;
    }
}
