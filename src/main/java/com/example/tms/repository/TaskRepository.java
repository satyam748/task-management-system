package com.example.tms.repository;

import com.example.tms.entity.TaskEntity;
import com.example.tms.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>, JpaSpecificationExecutor<TaskEntity> {
    Page<TaskEntity> findAll(Pageable pageable);
    Page<TaskEntity> findAllByAssignedUser(UserEntity user, Pageable pageable);
}
