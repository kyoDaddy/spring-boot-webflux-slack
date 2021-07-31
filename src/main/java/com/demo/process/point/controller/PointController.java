package com.demo.process.point.controller;

import com.demo.process.point.domain.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/points")
public class PointController {


    @GetMapping
    public Flux<Point> findAll() {


        return null;
    }

    @GetMapping("/{memId}")
    public Mono<Point> findById(@PathVariable("memId") final int memId) {


        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Point> crate(@RequestBody final Point point) {


        return null;
    }

    @PutMapping("/{memId}")
    public Mono<Point> update(@PathVariable("memId") final int memId, @RequestBody final Point point) {


        return null;
    }

    @DeleteMapping("/{memId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable("memId") final String id) {


        return null;
    }

    @GetMapping("/me")
    public Mono<Point> getPoint() {


        return null;
    }



}
