package com.demo.process.controller;

import com.demo.config.prop.SlackProp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final SlackProp slackProp;

    /**
     * Mono는 0 또는 1, 없음 또는 있음 등의 둘중 하나를 제공
     * @param exchange
     * @return
     */
    @GetMapping({"", "/"})
    public Mono<String> home(final ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        log.info("path => {}", request.getPath().value());
        return exchange.getSession()
                .map(session -> {
                    return "Hello world, session id => " + session.getId();
                });
    }

    @GetMapping({"/prop"})
    public Mono<SlackProp> propTest() {
        return Mono.just(slackProp);
    }


    /**
     * flux는 값을 뽑아주는 Iterator(이터레이터)
     * @return
     */
    @GetMapping("/days")
    public Flux<String> days() {
        return Flux.fromIterable(Arrays.asList(
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        ));
    }

    @GetMapping("/months")
    public Mono<List> months() {
        return Mono.just(Arrays.asList(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        ));
    }





}