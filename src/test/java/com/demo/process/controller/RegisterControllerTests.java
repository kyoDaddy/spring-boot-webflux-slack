package com.demo.process.controller;

import com.demo.process.model.RegisterForm;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = RegisterController.class)
@AutoConfigureWebFlux
@ActiveProfiles("local")
@ComponentScan(basePackages = {"com.demo"})
public class RegisterControllerTests {

    @Autowired
    private WebTestClient client;


    @Test
    void testRegister() {
        JsonObject jObj = new JsonObject();
        jObj.addProperty("email", "test@test.com");
        jObj.addProperty("password", "11231415@1");
        jObj.addProperty("confirmPassword", "11231415@1");

        this.client.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(jObj.toString()), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }

    @Test
    void testRegister1() {

        MultiValueMap map = new LinkedMultiValueMap();
        map.add("email", "test@test.cocm");
        map.add("password", "11231415@1");
        map.add("confirmPassword", "11231415@1");

        this.client.post()
                .uri("/register1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }


    @Test
    void testRegister2() {

        MultiValueMap map = new LinkedMultiValueMap();
        map.add("email", "test@test.cocm");
        map.add("password", "11231415@1");
        map.add("confirmPassword", "11231415@1");

        this.client.post()
                .uri("/register2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(map))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }

    @Test
    void testRegister3() {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("email", "test@test.cocm");
        builder.part("password", "11231415@1");
        builder.part("confirmPassword", "11231415@1");

        this.client.post()
                .uri("/register3")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }



}
