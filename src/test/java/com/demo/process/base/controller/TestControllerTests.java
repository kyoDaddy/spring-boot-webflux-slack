package com.demo.process.base.controller;

import com.demo.config.prop.SlackProperties;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = TestController.class)
@AutoConfigureWebFlux
@ActiveProfiles("local")
@ComponentScan(basePackages = {"com.demo"})
public class TestControllerTests {
/*
    @MockBean
    private GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;

    @MockBean
    private SlackProp slackProp;
*/

    @Autowired
    private WebTestClient client;


    @Test
    void testHome() {
        this.client.get()
            .uri("/test")
            .exchange()
            .expectBody(String.class)
            .consumeWith(result -> {
                System.out.println(result);
            });
    }

    @Test
    void testProp() {

        this.client.get()
                .uri("/test/prop")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(SlackProperties.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });
    }


    @Test
    void testDays() {
        this.client.get()
                .uri("/test/days")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }


    @Test
    void testMonths() {
        this.client.get()
                .uri("/test/months")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }


    @Test
    void testLocales() {
        this.client.get()
                .uri("/test/locales")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }

    @Test
    void testLocalesDefault() {
        this.client.get()
                .uri("/test/locales/default")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }

    @Test
    void testAvailableTimeZones() {
        this.client.get()
                .uri("/test/time-zones")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }

    @Test
    void testCurrentDateTime() {

        this.client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/test/time-zones/current-datetime")
                                .queryParam("timeZone", "UTC")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(result -> {
                    System.out.println(result);
                });

    }





}
