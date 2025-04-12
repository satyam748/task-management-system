package com.example.tms.mapper;

import com.example.tms.dto.TaskDto;
import com.example.tms.entity.TaskEntity;

public class TaskMapper {

    public static TaskDto convertToDto(TaskEntity task)
    {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignedUser() != null ? task.getAssignedUser().getId() : null,
                task.getAttachmentPath()
        );
    }

}
