package com.demo.process.user.service;

import com.demo.process.base.model.RegisterRequest;
import com.demo.process.base.model.RegisterResponse;
import com.demo.process.user.model.UpdateUserRequest;
import com.demo.process.user.model.UserResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Mono<List<UserResponse>> findAll();

    Mono<UserResponse> findById(final UUID id);

    Mono<RegisterResponse> create(RegisterRequest user);

    Mono<UserResponse> update(final UpdateUserRequest user);

    Mono<Void> deleteById(final UUID id);
}
