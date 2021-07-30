package com.demo.process.controller;

import com.demo.process.model.SendEmailForm;
import com.demo.process.service.SendService;
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
    public Mono<Void> sendMail(@RequestBody SendEmailForm req) {
        log.info("email => {}", req.getEmail());
        return senderService.sendMail(
                SendEmailForm.builder()
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
