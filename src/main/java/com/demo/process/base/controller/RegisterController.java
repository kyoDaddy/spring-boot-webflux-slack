package com.demo.process.base.controller;

import com.demo.process.base.model.RegisterRequest;
import com.demo.process.base.model.RegisterResponse;
import com.demo.process.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "가입")
    public Mono<RegisterResponse> create(@RequestBody @Validated final RegisterRequest user) {
        log.info("email => {}, password => {}", user.getEmail(), user.getPassword());
        return userService.create(user);
        //return Mono.just(user);
        /*
        return exchange.getFormData()
            .map(formData -> {
                return RegisterRequest.builder()
                        .email(formData.getFirst("email"))
                        .password(formData.getFirst("password"))
                        .confirmPassword(formData.getFirst("confirmPassword"))
                        .build();
            });
         */

    }

}
