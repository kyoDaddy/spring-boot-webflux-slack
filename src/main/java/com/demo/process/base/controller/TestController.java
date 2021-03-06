package com.demo.process.base.controller;

import com.demo.config.prop.SlackProperties;
import com.demo.process.base.model.TimeZoneResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final SlackProperties slackProperties;

    /**
     * Mono에서의 행위는 0 또는 1, 없음 또는 있음 등의 둘중 하나를 제공
     *
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
    public Mono<SlackProperties> propTest() {
        return Mono.just(slackProperties);
    }


    /**
     * flux는 값을 뽑아주는 Iterator (행위는 0~n개)
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

    @GetMapping("/locales")
    public Flux<Locale> getAvailableLocales() {
        return Flux.fromIterable(Arrays.asList(Locale.getAvailableLocales()));
    }

    @GetMapping("/locales/default")
    public Mono<Locale> getLocaleDefault() {
        return Mono.just(Locale.getDefault());
    }

    @GetMapping("/time-zones")
    public Flux<TimeZone> getAvailableTimeZones() {
        return Flux.fromIterable(Arrays.asList(TimeZone.getAvailableIDs()))
                .map(TimeZone::getTimeZone);
    }

    @GetMapping("/time-zones/default")
    public Mono<TimeZone> getTimeZoneDefault() {
        return Mono.just(TimeZone.getDefault());
    }

    @GetMapping("/time-zones/current-datetime")
    public Mono<TimeZoneResponse> currentDateTime(@RequestParam(required = false, defaultValue = "Asia/Seoul", value = "timeZone") final String timeZone) {

        log.info("========>" + timeZone);
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        log.info("change Default Time Zone => {}", TimeZone.getDefault().getID());

        return Mono.just(
                TimeZoneResponse.builder()
                        .instant(Instant.now())
                        .localDate(LocalDate.now())
                        .offsetDateTime(OffsetDateTime.now())
                        .offsetTime(OffsetTime.now())
                        .zonedDateTime(ZonedDateTime.now())
                        .build()
        );

    }


}
