package com.example.tms.services;

import com.example.tms.Response;
import com.example.tms.enums.Role;
import com.example.tms.repository.UserRepository;
import com.example.tms.dto.UserDto;
import com.example.tms.entity.UserEntity;
import com.example.tms.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Response<UserDto> createUser(UserEntity user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);
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
