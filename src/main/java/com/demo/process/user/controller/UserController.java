package com.demo.process.user.controller;

import com.demo.process.user.UserService;
import com.demo.process.user.domain.User;
import com.demo.process.user.model.UserResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public Mono<UserDetails> getUserDetails() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(authentication -> (UserDetails) authentication.getDetails());
    }

    @GetMapping
    @ApiOperation(value = "모든 사용자 정보 검색")
    public Mono<List<User>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "아이디로 사용자 정보 가져오기")
    public Mono<User> findById(@PathVariable("id") final UUID id) {
        return userService.findById(id);
    }



/*
    @GetMapping("")
    public Flux<UserResponse> findAll() {
        return Flux.just(
                UserResponse.builder()
                        .id(UUID.randomUUID())
                        .username("user 1")
                        .email("test1@test.com")
                        .build(),
                UserResponse.builder()
                        .id(UUID.randomUUID())
                        .username("user 2")
                        .email("test2@test.com")
                        .build(),
                UserResponse.builder()
                        .id(UUID.randomUUID())
                        .username("user 3")
                        .email("test3@test.com")
                        .build()
        );
    }
*/


}
