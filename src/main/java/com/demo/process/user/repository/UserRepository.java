package com.demo.process.user.repository;

import com.demo.process.user.domain.User;
import com.demo.process.user.model.CreateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);

}
