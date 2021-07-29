package com.demo.config.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * mvc 패턴으로 비교하면 service 역할을 하게 될 것...
 */
@Component
public class HelloHandler {

    private HashMap<Object, Object> result = new HashMap<>();
    private Mono<HashMap<Object, Object>> mapper = Mono.just(result);

    public Mono<ServerResponse> hello(ServerRequest request) {
        /*
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, " + LocalDateTime.now()));
        */

        result.put("number", 1234);
        result.put("text", "webflux");
        mapper.subscribe( (arg)-> {
            System.out.println(arg);
        });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(mapper, HashMap.class));

    }

}
