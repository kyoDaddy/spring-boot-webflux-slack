package com.demo.config.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProp {

    private String secretKey;

    private int expiresMinutes;

    @PostConstruct
    public void showProperties() {

        log.debug("spring.jwt.secretKey => {}", secretKey);
        log.debug("spring.jwt.expiresMinutes => {}", expiresMinutes);

    }


}
