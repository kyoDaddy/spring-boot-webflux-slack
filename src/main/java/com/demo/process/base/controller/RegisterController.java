package com.demo.process.base.controller;

import com.demo.config.exception.UnauthorizedException;
import com.demo.process.base.model.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class RegisterController {

    @PostMapping("/register")
    public void register(@RequestBody @Validated RegisterForm req) {

        int currentTm = LocalDateTime.now().getHour();
        if(currentTm < 12) {
            throw new UnauthorizedException(HttpStatus.BAD_REQUEST, "현재 회원가입이 불가능한 시각입니다.");
        }

        log.debug("email => {}", req.getEmail());
        log.debug("password => {}", req.getPassword());
    }

    @PostMapping(value = "/register1", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Mono<RegisterForm> register1(final RegisterForm form) {
        return Mono.just(form);
    }

    @PostMapping(value = "/register2", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Mono<RegisterForm> register2(final ServerWebExchange exchange) {
        return exchange.getFormData()
                .map(formData -> {
                    return RegisterForm.builder()
                            .email(formData.getFirst("email"))
                            .password(formData.getFirst("password"))
                            .confirmPassword(formData.getFirst("confirmPassword"))
                            .build();
                });
    }

    @PostMapping(value = "/register3", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Mono<RegisterForm> register3(final RegisterForm form) {
        return Mono.just(form);
    }



}
