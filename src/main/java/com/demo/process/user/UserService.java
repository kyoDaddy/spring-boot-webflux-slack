package com.demo.process.user;

import com.demo.process.user.domain.User;
import com.demo.process.user.model.CreateUser;
import com.demo.process.user.model.UpdateUser;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Mono<List<User>> findAll();

    Mono<User> findById(final UUID id);

    Mono<User> create(final CreateUser user);

    Mono<User> update(final UpdateUser user);

    Mono<Void> deleteById(final UUID id);
}
