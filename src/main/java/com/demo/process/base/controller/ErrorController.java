package com.demo.process.base.controller;

import com.demo.config.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/errors")
public class ErrorController {

    @GetMapping({"", "/"})
    public Mono<String> notAuth() {
        throw new UnauthorizedException(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/invalidUsernamePassword")
    public Mono<String> invalidUsernamePassword() {
        throw new UnauthorizedException(HttpStatus.TOO_MANY_REQUESTS, "그만호출해!!!");
    }

    @GetMapping("/serverError")
    public Mono<String> serverError() {
        throw new RuntimeException();
    }


}
