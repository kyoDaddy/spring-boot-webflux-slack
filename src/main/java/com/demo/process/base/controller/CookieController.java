package com.demo.process.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/cookies")
public class CookieController {

    @GetMapping("")
    public Mono<String> getCookie(@CookieValue(value="access_token", defaultValue = "") final String accessToken) {
        return Mono.just("cookie value = > " + accessToken);
    }

    @GetMapping("/create")
    public Mono<ResponseCookie> createCookie(final ServerWebExchange exchange) {
        final String accessToken = UUID.randomUUID().toString();
        final ResponseCookie cookie = ResponseCookie.from("access_token", accessToken).build();
        exchange.getResponse().addCookie(cookie);
        return Mono.just(cookie);
    }

    @GetMapping("/invalidate")
    public Mono<String> invalidateCookie(@CookieValue(value = "access_token", defaultValue = "") final String accessToken, final ServerWebExchange exchange) {
        final ResponseCookie cookie = ResponseCookie.from("access_token", "").maxAge(0).build();
        exchange.getResponse().addCookie(cookie);
        return Mono.just("invalidate cookie => " + accessToken);
    }

}
