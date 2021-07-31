package com.demo.process.login.controller;

import com.demo.process.login.LoginService;
import com.demo.process.login.model.LoginRequest;
import com.demo.process.login.model.LoginResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = {"Authentication"})
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody final LoginRequest request) {
        return loginService.login(request);
    }

}
