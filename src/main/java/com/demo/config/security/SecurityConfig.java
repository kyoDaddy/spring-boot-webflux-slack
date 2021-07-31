package com.demo.config.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.demo.config.prop.JwtProp;
import com.demo.process.security.AuthTokenService;
import com.demo.process.security.filter.AuthTokenWebFilter;
import com.demo.process.security.handler.AuthServerAccessDeniedHandler;
import com.demo.process.security.impl.AuthTokenServiceImpl;
import com.demo.process.security.impl.DefaultUserDetailsJwtClaimsConverterImpl;
import com.demo.process.security.repository.AuthServerSecurityContextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Slf4j
@EnableWebFluxSecurity // @EnableWebFluxSecurity는 Spring Security를 활성화
public class SecurityConfig {

    @Autowired
    private JwtProp jwtProp;


    private final String[] PUBLIC_ACCESS_PATHS = new String[]{
        "/webjars/springfox-swagger-ui/**",
        "/swagger-ui/**",
        "/v3/api-docs",
        "/v3/api-docs/_",
        "/swagger-resources",
        "/swagger-resources/**",
        "/favicon.ico",
        "/assets/**",
        "/public/**",
        "/auth/**",
        "/"
    };

    @Bean
    public AuthTokenService authTokenService() {
        final Algorithm algorithm = Algorithm.HMAC256(jwtProp.getSecretKey());
        return new AuthTokenServiceImpl(algorithm, jwtProp.getExpiresMinutes());
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        return http
                .securityContextRepository(new AuthServerSecurityContextRepository())           // 서버부담 줄이기, cache 서버를 사용을 위함
                .exceptionHandling().accessDeniedHandler(new AuthServerAccessDeniedHandler())
                .and()
                .logout().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(PUBLIC_ACCESS_PATHS).permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAt(
                        new AuthTokenWebFilter(
                                authTokenService(),
                                new DefaultUserDetailsJwtClaimsConverterImpl(),
                                new AuthServerSecurityContextRepository()
                        ),
                        SecurityWebFiltersOrder.AUTHENTICATION
                )
                .build();

    }


    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService(final PasswordEncoder passwordEncoder) {
        return username -> {
          log.info("login with username => {}", username);
          return Mono.just(
                  User.withUsername(username)
                        .password(passwordEncoder.encode("password"))
                        .authorities(Collections.emptyList())
                        .build()
          );
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
