package com.example.tms.mapper;

import com.example.tms.dto.UserDto;
import com.example.tms.entity.TaskEntity;
import com.example.tms.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto convertToDto(UserEntity user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTasks() != null ? user.getTasks().stream().map(TaskEntity::getId).collect(Collectors.toList()) : null
        );
    }
}
