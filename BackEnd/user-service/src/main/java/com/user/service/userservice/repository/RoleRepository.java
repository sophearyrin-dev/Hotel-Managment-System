package com.user.service.userservice.repository;

import com.user.service.userservice.models.ERole;
import com.user.service.userservice.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
