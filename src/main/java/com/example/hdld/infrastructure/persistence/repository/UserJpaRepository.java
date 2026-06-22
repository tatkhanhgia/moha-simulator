package com.example.hdld.infrastructure.persistence.repository;

import com.example.hdld.infrastructure.persistence.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByUsername(String username);
}
