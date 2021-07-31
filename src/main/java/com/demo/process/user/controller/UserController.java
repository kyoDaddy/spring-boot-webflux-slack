package com.demo.process.user.controller;

import com.demo.process.user.UserService;
import com.demo.process.user.domain.User;
import com.demo.process.user.model.CreateUser;
import com.demo.process.user.model.UpdateUser;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "사용자 정보 생성")
    public Mono<User> create(@RequestBody @Validated final CreateUser user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "아이디로 사용자 정보 업데이트")
    public Mono<User> update(@PathVariable("id") final UUID id, @RequestBody @Validated final UpdateUser user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "아이디로 사용자 데이터 삭제")
    public Mono<Void> deleteById(@PathVariable("id") final UUID id) {
        return userService.deleteById(id);
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
