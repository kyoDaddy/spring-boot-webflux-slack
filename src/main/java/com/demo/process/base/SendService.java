package com.demo.process.base;

import com.demo.config.exception.UnauthorizedException;
import com.demo.config.prop.SlackProp;
import com.demo.process.base.model.SendEmailForm;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.mail.internet.MimeMessage;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
public class SendService {

    private final JavaMailSender javaMailSender;

    private final SlackProp slackProp;

    public Mono<Void> sendMail(final SendEmailForm sendForm) {
        return Mono.fromRunnable(() -> {
            try {
                final MimeMessage msg = javaMailSender.createMimeMessage();
                //msg.setFrom(new InternetAddress(from));

                // true = multipart message
                final MimeMessageHelper helper = new MimeMessageHelper(msg, true);
                helper.setTo(sendForm.getEmail());
                helper.setSubject(sendForm.getSubject());

                // 메일 내용 html 형식 여부
                helper.setText(sendForm.getBody(), true);
                helper.setSentDate(new Date());

                javaMailSender.send(msg);
                log.info("==========> 성공");

            } catch (Exception e) {
                log.warn("sendEmail error => ", e);
                throw new UnauthorizedException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        })
        .subscribeOn(Schedulers.boundedElastic())
        .then();

    }

    // return value 에 관심이 없는 메서드이므로 Mono<Void> 반환
    public Mono<Void> sendSlack(final String text) {
        // subscribe 될 때까지 기다리기 위해 Mono.fromRunnable 메서드를 이용하여 래핑
        return Mono.fromRunnable(() -> {
            try {

                Slack slack = new Slack();
                String webHookUrl = slackProp.getProjectDemo().getInfo();

                Payload payload = Payload.builder()
                                    .text(text)
                                    .build();

                WebhookResponse response = slack.send(webHookUrl, payload);
                log.info("==>" + response.getMessage());

            } catch (Exception e) {
                log.warn("sendSlack error => ", e);
                throw new UnauthorizedException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        })
        .subscribeOn(Schedulers.boundedElastic())
        .then();

    }


}
