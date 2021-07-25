package com.demo.process.controller;

import com.demo.process.domain.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/points")
public class PointController {

    private final Point ME = Point.builder()
            //.poiId(UUID.randomUUID().toString())
            .poiContent("test")
            .poiDtm(LocalDate.now())
            .memId(UUID.randomUUID().variant())
            .poiType("DEFAULT")
            .poiAction("PLUS")
            .build();


    @GetMapping
    public Flux<Point> findAll() {
        log.info("find all points in database...");
        return Flux.just(
                ME,
                Point.builder()
                .poiContent("test")
                .poiDtm(LocalDate.now())
                .memId(UUID.randomUUID().variant())
                .poiType("DEFAULT")
                .poiAction("PLUS")
                .build()
        );
    }






}
