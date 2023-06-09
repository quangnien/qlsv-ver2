package com.example.demo.repository;

import com.example.demo.entity.user.ERole;
import com.example.demo.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleEntity, String> {
  Optional<RoleEntity> findByName(ERole name);
}
