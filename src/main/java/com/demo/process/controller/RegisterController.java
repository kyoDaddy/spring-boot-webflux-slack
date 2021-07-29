package com.demo.process.controller;

import com.demo.config.exception.UnauthorizedException;
import com.demo.process.model.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
