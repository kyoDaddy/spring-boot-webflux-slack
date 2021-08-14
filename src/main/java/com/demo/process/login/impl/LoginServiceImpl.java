package com.demo.process.login.impl;

import com.demo.config.exception.UnauthorizedException;
import com.demo.config.prop.JwtProp;
import com.demo.process.login.LoginService;
import com.demo.process.login.model.LoginRequest;
import com.demo.process.login.model.LoginResponse;
import com.demo.process.security.AuthTokenService;
import com.demo.process.security.DefaultUserDetailsJwtClaimsConverter;
import com.demo.process.security.impl.DefaultUserDetails;
import com.demo.process.user.domain.User;
import com.demo.process.user.model.UserResponse;
import com.demo.process.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtProp jwtProp;

    private final AuthTokenService authTokenService;

    private final DefaultUserDetailsJwtClaimsConverter defaultUserDetailsJwtClaimsConverter;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(!user.isPresent()) {
            return Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "등록된 회원이 없습니다."));
        }
        else if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."));
        }

        final DefaultUserDetails userDetails = DefaultUserDetails.builder()
                .id(UUID.randomUUID())
                .authorities(Arrays.asList("ADMIN"))
                .build();

        final Map<String, Object> claims = defaultUserDetailsJwtClaimsConverter.convert(userDetails);

        return authTokenService.sign(claims).log()
                .map(token -> {
                    return LoginResponse.builder()
                            .expiresIn(jwtProp.getExpiresMinutes() * 60L)
                            .tokenType("bearer")
                            .accessToken(token)
                            .build();
                });

    }
}
