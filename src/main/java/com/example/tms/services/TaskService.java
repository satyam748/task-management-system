package com.example.tms.services;

import com.example.tms.Response;
import com.example.tms.enums.Role;
import com.example.tms.repository.TaskRepository;
import com.example.tms.TaskSpecification;
import com.example.tms.repository.UserRepository;
import com.example.tms.dto.TaskDto;
import com.example.tms.entity.TaskEntity;
import com.example.tms.entity.UserEntity;
import com.example.tms.mapper.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private final TaskRepository taskRepo;

    private final UserRepository userRepo;

    // Constructor injection. No need to use Autowired explicitly when having single constructor
    public TaskService(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public Response<List<TaskDto>> getAllTask(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isAdminOrManager = authorities.stream().anyMatch(auth-> auth.getAuthority().equals(Role.ROLE_ADMIN.name()) ||
                auth.getAuthority().equals(Role.ROLE_MANAGER.name()));

        List<TaskEntity> tasks = null;

        if(isAdminOrManager) {
            tasks = taskRepo.findAll();
        }else{
            Optional<UserEntity> user= userRepo.findByUsername(loggedInUsername);
            if (user.isPresent())
                tasks = user.get().getTasks();
            else
                return Response.error("User not found", null, "E1002");
        }
        if (tasks == null || tasks.isEmpty())
            return Response.error("No tasks found", null, "E1000");

        List<TaskDto> tasksDto = tasks.stream().map(TaskMapper::convertToDto).collect(Collectors.toList());
        return Response.success("Tasks retrieved", tasksDto);
    }

    public Response<Page<TaskDto>> getPaginatedTasks(int page, int size, String sortBy, String sortDir){
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean isAdminOrManager = user.getRole().equals(Role.ROLE_ADMIN) || user.getRole().equals(Role.ROLE_MANAGER);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<TaskEntity> taskPages;
        if (isAdminOrManager)
            taskPages = taskRepo.findAll(pageable);
        else
            taskPages = taskRepo.findAllByAssignedUser(user, pageable);

        Page<TaskDto> taskDtoPage = taskPages.map(TaskMapper::convertToDto);

        return Response.success("Task retrieved successfully", taskDtoPage);

    }

    public Response<List<TaskDto>> getTasks(Map<String, String> filters){
        Specification<TaskEntity> spec = TaskSpecification.getFilteredTasks(filters);
        List<TaskEntity> tasks = taskRepo.findAll(spec);
        List<TaskDto> tasksDto = tasks.stream().map(TaskMapper::convertToDto).collect(Collectors.toList());
        return Response.success("Tasks retrieved successfully", tasksDto);
    }

    public Response<TaskDto> createTask(TaskEntity task){
        TaskEntity newTask =  taskRepo.save(task);
        return Response.success("Task created successfully", TaskMapper.convertToDto(newTask));
    }

    public Response<TaskDto> updateTask(int id, TaskEntity task){
        Optional<TaskEntity> existingTask_ = taskRepo.findById(id);

        if (existingTask_.isPresent()){
            TaskEntity existingTask = existingTask_.get();
            if(task.getTitle() != null)
                existingTask.setTitle(task.getTitle());
            if(task.getDescription() != null)
                existingTask.setDescription(task.getDescription());
            if(task.getStatus() != null)
                existingTask.setStatus(task.getStatus());
            if(task.getCreatedAt() != null)
                existingTask.setCreatedAt(task.getCreatedAt());
            TaskEntity updatedTask = taskRepo.save(existingTask);
            return Response.success("Task updated successfully", TaskMapper.convertToDto(updatedTask));
        }
        else
            return Response.error("Task with id {} not found" + id, null, "E1001");
    }

    public Response<TaskDto> deleteTaskById(int id){
        Optional<TaskEntity> existingTask = taskRepo.findById(id);

        if(existingTask.isPresent())
        {
            TaskEntity task = existingTask.get();
            task.setDeleted(true);
            taskRepo.save(task);
            return Response.success("Task deleted successfully", TaskMapper.convertToDto(task));
        }
        else
            return Response.error("Task not found with id: " + id, null, "E1003");

    }

    public Response<TaskDto> assignTaskToUser(int taskId, int userId)
    {
        Optional<UserEntity> user = userRepo.findById(userId);
        Optional<TaskEntity> CurrentTask = taskRepo.findById(taskId);

        if(user.isPresent() && CurrentTask.isPresent()) {
            TaskEntity task = CurrentTask.get();
            task.setAssignedUser(user.get());
            taskRepo.save(task);
            return Response.success("Assigned task to user successfully", TaskMapper.convertToDto(task));
        }
        else if (user.isEmpty())
            return Response.error("User not found", null, "E1004");
        else
            return Response.error("Task not found", null, "E1005");
    }




}
