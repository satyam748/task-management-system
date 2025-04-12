package com.example.tms.controller;

import com.example.tms.Response;
import com.example.tms.dto.TaskDto;
import com.example.tms.dto.UserDto;
import com.example.tms.entity.TaskEntity;
import com.example.tms.entity.UserEntity;
import com.example.tms.services.FileStorageService;
import com.example.tms.services.TaskService;
import com.example.tms.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    private final FileStorageService fileStorageService;

    public TaskController(TaskService taskService, UserService userService, FileStorageService fileStorageService){
        this.taskService = taskService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("tasks/getAll")
    public Response<List<TaskDto>> getAllTasks(){
        return taskService.getAllTask();
    }

    @GetMapping("tasks/getAllPaginated")
    public Response<Page<TaskDto>> getPaginatedTask(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ){
        return taskService.getPaginatedTasks(page, size, sortBy, sortDir);
    }

    @PostMapping("tasks/create")
    public Response<TaskDto> createTask(@RequestBody TaskEntity task) {
        return taskService.createTask(task);
    }

    @PutMapping("tasks/update/{id}")
    public  Response<TaskDto> updateTask(@PathVariable int id,@RequestBody TaskEntity task){
        return taskService.updateTask(id, task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("tasks/delete/{id}")
    public Response<TaskDto> deleteTaskById(@PathVariable int id){
        return taskService.deleteTaskById(id);
    }

    @GetMapping("tasks/filter")
    public Response<List<TaskDto>> getTasks(@RequestParam Map<String, String> filters){
        return taskService.getTasks(filters);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users/get")
    public Response<List<UserDto>> getUsers(){
        return userService.getAllUser();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("users/create")
    public Response<UserDto> createUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("tasks/{taskId}/assign/{userId}")
    public Response<TaskDto> assignTaskToUser(@PathVariable int taskId, @PathVariable int userId) {
        return taskService.assignTaskToUser(taskId, userId);
    }

    @PostMapping("tasks/{taskId}/uploadFile")
    public Response<String> uploadFile(@PathVariable int taskId, @RequestParam("file") MultipartFile file){
        return fileStorageService.uploadFile(taskId, file);
    }

    @GetMapping("tasks/getFile/{filename}")
    public Resource getFile(@PathVariable String filename){
        return fileStorageService.getFile(filename);
    }

    @GetMapping("tasks/listFiles")
    public List<String> getFile(){
        return fileStorageService.listFiles();
    }
}
