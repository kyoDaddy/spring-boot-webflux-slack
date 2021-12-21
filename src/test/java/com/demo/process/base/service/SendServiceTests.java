package com.demo.process.base.service;


import com.demo.process.base.model.SendEmailRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

/**
 * intellij 에서 junit5 test 할때 아래 에러 남....
 *  No tests found for given includes:filter.includeTestsMatching
 *  Settings > Build,Execution,Deployment > Build Tools > Gradle > Run tests usingL InteliJ IDEA 로 변경 후 정상처리
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
@ContextConfiguration(classes = {SendService.class})
@ComponentScan(basePackages = {"com.demo"})
public class SendServiceTests {

    @Autowired
    private SendService sendService;

    @Before
    public void setUp() {

    }


    @Test
    public void testSendMail() {

        Mono<Void> post = sendService.sendMail(
                SendEmailRequest.builder()
                        .email("rlarbghrbgh@gmail.com")
                        .subject("test")
                        .body("body content")
                        .build()
        );

    }

    @Test
    public void testSendSlack() {

        Mono<Void> post = sendService.sendSlack(
                "etet"
        );

    }


}
