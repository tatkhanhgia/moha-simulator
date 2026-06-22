package com.example.hdld.infrastructure.persistence.adapter;

import com.example.hdld.domain.entity.User;
import com.example.hdld.domain.repository.UserRepository;
import com.example.hdld.infrastructure.persistence.entity.UserJpa;
import com.example.hdld.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpa jpa = toJpa(user);
        UserJpa saved = jpaRepository.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private UserJpa toJpa(User user) {
        UserJpa jpa = new UserJpa();
        jpa.setId(user.getId());
        jpa.setUsername(user.getUsername());
        jpa.setPasswordHash(user.getPasswordHash());
        jpa.setRole(user.getRole());
        return jpa;
    }

    private User toDomain(UserJpa jpa) {
        return new User(jpa.getId(), jpa.getUsername(), jpa.getPasswordHash(), jpa.getRole());
    }
}
