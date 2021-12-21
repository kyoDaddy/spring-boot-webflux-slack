package com.demo.process.base.controller;

import com.demo.process.base.model.SendEmailRequest;
import com.demo.process.base.service.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class SendController {

    private final SendService senderService;

    @PostMapping("/email")
    public Mono<Void> sendMail(@RequestBody SendEmailRequest req) {
        log.info("email => {}", req.getEmail());
        return senderService.sendMail(
                SendEmailRequest.builder()
                        .email(req.getEmail())
                        .subject(req.getSubject())
                        .body(req.getBody())
                        .build()
        );
    }

    @PostMapping(value = "/slack")
    public Mono<Void> sendSlack(@RequestParam(required = false, value = "message") final String message) {

        if(message.isEmpty()) {
            return Mono.empty();
        }
        else {
            return senderService.sendSlack(
                    message
            );
        }

    }


}
