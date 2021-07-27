package com.demo.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        final CorsConfiguration config = new CorsConfiguration();

        // 모든 출처(모든 도메인 이름) 어디에서나 Ajax를 호출할 수 있도록 허용
        config.setAllowedOrigins(Arrays.asList("*"));
        // 1시간 동안 캐시된 실행 전 요청(OPTIONS)을 수행
        config.setMaxAge(Duration.ofMinutes(1));
        // 선언한 Http 메소드를 사용하여 Ajax를 호출 허용
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.HEAD.name()
        ));
        // 선언한 헤더가 허용
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.IF_MATCH,
                HttpHeaders.IF_MODIFIED_SINCE,
                HttpHeaders.IF_NONE_MATCH,
                HttpHeaders.IF_UNMODIFIED_SINCE,
                "X-Requested-With"
        ));
        // 클라이언트가 다음 응답 헤더를 읽을 수 있도록 허용 (cors response header 이슈 있을 경우 사용)
        config.setExposedHeaders(Arrays.asList(
                HttpHeaders.ETAG,
                HttpHeaders.LINK,
                "X-RateLimit-Limit",
                "X-RateLimit-Remaining",
                "X-RateLimit-Reset"
        ));

        // 모든 경로에서 Cors 사용
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}
