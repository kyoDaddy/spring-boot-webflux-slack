package com.demo.config.router;

import com.demo.config.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

    /**
     * helloHandler 클래스를 RouterFunctions 인터페이스에 넣어 전달..
     * mvc 패턴으로비교하면, Controller 역할을 담당...
     *
     * @param helloHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), helloHandler::hello);

    }

}
