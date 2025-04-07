package com.example.tms;

import com.example.tms.dto.TaskDto;
import com.example.tms.dto.UserDto;
import com.example.tms.entity.TaskEntity;
import com.example.tms.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("tasks")
    public Response<List<TaskDto>> getAllTasks(){
        return taskService.getAllTask();
    }

    @PostMapping("tasks")
    public Response<TaskDto> createTask(@RequestBody TaskEntity task) {
        return taskService.createTask(task);
    }

    @PutMapping("tasks/update/{id}")
    public  Response<TaskDto> updateTask(@PathVariable int id,@RequestBody TaskEntity task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("tasks/delete/{id}")
    public Response<TaskDto> deleteTaskById(@PathVariable int id){
        return taskService.deleteTaskById(id);
    }

    @GetMapping("tasks/filter")
    public Response<List<TaskDto>> getTasks(@RequestParam Map<String, String> filters){
        return taskService.getTasks(filters);
    }

    @GetMapping("users")
    public Response<List<UserDto>> getUsers(){
        return userService.getAllUser();
    }

    @PostMapping("users")
    public Response<UserDto> createUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }

    @PostMapping("tasks/{taskId}/assign/{userId}")
    public Response<TaskDto> assignTaskToUser(@PathVariable int taskId, @PathVariable int userId) {
        return taskService.assignTaskToUser(taskId, userId);
    }


}
