package com.example.tms;

import com.example.tms.dto.UserDto;
import com.example.tms.entity.UserEntity;
import com.example.tms.mapper.TaskMapper;
import com.example.tms.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Response<UserDto> createUser(UserEntity user){
        UserEntity newUser =  userRepo.save(user);
        return Response.success("User created successfully", UserMapper.convertToDto(newUser));
    }

    public Response<List<UserDto>> getAllUser(){
        List<UserEntity> users = userRepo.findAll();
        if(users.isEmpty())
            return Response.error("No user found", null, "E1000");
        List<UserDto> usersDto = users.stream().map(UserMapper::convertToDto).collect(Collectors.toList());
        return Response.success("Tasks retrieved successfully", usersDto);
    }

}
