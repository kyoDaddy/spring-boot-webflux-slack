package com.demo.process.user.impl;

import com.demo.process.user.UserService;
import com.demo.process.user.domain.User;
import com.demo.process.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<List<User>> findAll() {
        return Mono.fromCallable(() -> {
            return userRepository.findAll();
        });
    }

    @Override
    public Mono<User> findById(UUID id) {
        log.info("findById(UUID) userId");
        return Mono.fromCallable(() -> {
            return userRepository.findById(id).get();
        });
    }
}
