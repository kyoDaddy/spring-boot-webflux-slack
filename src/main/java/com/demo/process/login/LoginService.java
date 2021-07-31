package com.demo.process.login;

import com.demo.process.login.model.LoginRequest;
import com.demo.process.login.model.LoginResponse;
import reactor.core.publisher.Mono;

public interface LoginService {

    Mono<LoginResponse> login(final LoginRequest request);

}
