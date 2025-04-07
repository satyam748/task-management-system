package com.example.tms.dto;

import java.util.List;

public class UserDto {

    private int id;
    private String name;
    private String email;
    private String username;
    private List<Integer> tasks;

    public UserDto(int id, String name, String email, String username, List<Integer> tasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }
}
