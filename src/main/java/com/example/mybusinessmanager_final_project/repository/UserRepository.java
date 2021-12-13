package com.example.mybusinessmanager_final_project.repository;

import com.example.mybusinessmanager_final_project.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional <UserEntity> findById(Long id);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @Query("SELECT U FROM UserEntity AS U WHERE U.id= :id")
    UserEntity findByUserId(Long id);
}


