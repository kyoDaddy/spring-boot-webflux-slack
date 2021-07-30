package com.demo.config.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class GmailProp {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender() {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);
        javaMailSender.setProtocol(host.substring(0, host.indexOf(".")));
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }



}
