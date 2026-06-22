package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.User;

import java.util.Optional;

/**
 * Port for user persistence operations.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
}
