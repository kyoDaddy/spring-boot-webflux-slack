package com.demo.process.user;

import com.demo.process.user.domain.User;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Mono<List<User>> findAll();

    Mono<User> findById(UUID id);

}
