package com.example.tms.dto;

import java.util.List;

public class UserDto {

    private int id;
    private String name;
    private String email;
    private List<Integer> tasks;

    public UserDto(int id, String name, String email, List<Integer> tasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }
}
