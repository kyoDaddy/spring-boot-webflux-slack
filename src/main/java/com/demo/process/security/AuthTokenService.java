package com.demo.process.security;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthTokenService {

    Mono<String> sign(final Map<String, Object> claims);

    Mono<Map<String, Object>> verify(final String token);

}
