package com.demo.process.user.impl;

import com.demo.config.exception.UnauthorizedException;
import com.demo.process.base.model.RegisterForm;
import com.demo.process.user.UserService;
import com.demo.process.user.domain.User;
import com.demo.process.user.model.CreateUser;
import com.demo.process.user.model.UpdateUser;
import com.demo.process.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<List<User>> findAll() {
        log.info("findAll");
        return Mono.fromCallable(() -> {
            return userRepository.findAll();
        });
    }

    @Override
    public Mono<User> findById(UUID id) {
        log.info("findById");
        return Mono.fromCallable(() -> {
            return userRepository.findById(id).get();
        });
    }

    @Transactional
    @Override
    public Mono<User> create(CreateUser user) {
        log.info("create");
        Optional<User> info = userRepository.findByEmail(user.getEmail()).ofNullable(null);
        if(info.isPresent()) {
            return Mono.error(new UnauthorizedException(HttpStatus.BAD_REQUEST, "해당 이메일로 등록된 회원이 존재합니다. (" + user.getEmail() + ")"));
        }
        return Mono.fromCallable(() -> {
            return userRepository.save(
                    User.builder()
                            .username(user.getUsername())
                            .password(passwordEncoder.encode(user.getPassword()))
                            .email(user.getEmail())
                            .build()
            );
        });
        //return Mono.just(userRepository.save(user).get());
    }

    @Transactional
    @Override
    public Mono<User> update(UpdateUser user) {
        log.info("update");
        Optional<User> info = userRepository.findById(user.getId());
        if(!info.isPresent()) {
            return Mono.error(new UnauthorizedException(HttpStatus.BAD_REQUEST, "해당 아이디로 등록된 회원이 존재하지 않습니다. (" + user.getId() + ")"));
        }
        // 객체가 변하면 hibernate 에서 자동으로 update 구문으로 변경한다.
        info.get().setUsername(user.getUsername());
        return Mono.fromCallable(() -> {
            return userRepository.save(
                        info.get()
            );
        });

    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        log.info("delete");
        return Mono.fromRunnable(() -> {
            userRepository.delete(
                    User.builder()
                    .id(id)
                    .build()
            );
        });
    }
}
