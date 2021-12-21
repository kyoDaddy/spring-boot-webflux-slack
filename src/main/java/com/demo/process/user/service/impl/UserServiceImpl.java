package com.demo.process.user.service.impl;

import com.demo.config.exception.UnauthorizedException;
import com.demo.process.base.model.RegisterRequest;
import com.demo.process.base.model.RegisterResponse;
import com.demo.process.user.model.UserResponse;
import com.demo.process.user.repository.mapper.UserMapper;
import com.demo.process.user.service.UserService;
import com.demo.process.user.repository.entity.User;
import com.demo.process.user.model.UpdateUserRequest;
import com.demo.process.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<List<UserResponse>> findAll() {
        log.info("findAll");
        return Mono.fromCallable(() -> userRepository.findAll().stream().map(
                user -> userMapper.userToUserResponse(user)).collect(Collectors.toList())
        ).log();
    }

    @Override
    public Mono<UserResponse> findById(UUID id) {
        log.info("findById");
        return Mono.fromCallable(() -> userMapper.userToUserResponse(
                        userRepository.findById(id).get()
                )
        ).log();

    }

    @Transactional
    @Override
    public Mono<RegisterResponse> create(RegisterRequest request) {
        log.info("create");
        Optional<User> info = userRepository.findByEmail(request.getEmail()).ofNullable(null);
        if(info.isPresent()) {
            return Mono.error(new UnauthorizedException(HttpStatus.BAD_REQUEST,
                    "해당 이메일로 등록된 회원이 존재합니다. (" + request.getEmail() + ")")
            );
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        return Mono.fromCallable(() -> userMapper.userToRegisterResponse(
                userRepository.save(
                    userMapper.registerRequestToUser(request)
                )
            )
        ).log();
        //return Mono.just(userRepository.save(user).get());
    }

    @Transactional
    @Override
    public Mono<UserResponse> update(UpdateUserRequest user) {
        log.info("update");
        Optional<User> info = userRepository.findById(user.getId());
        if(!info.isPresent()) {
            return Mono.error(new UnauthorizedException(HttpStatus.BAD_REQUEST,
                    "해당 아이디로 등록된 회원이 존재하지 않습니다. (" + user.getId() + ")")
            );
        }
        // 객체가 변하면 hibernate 에서 자동으로 update 구문으로 변경한다.
        info.get().setUsername(user.getUsername());

        return Mono.fromCallable(() -> userMapper.userToUserResponse(
                        userRepository.save(info.get())
                )
        ).log();
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        log.info("delete");
        return Mono.fromRunnable(() -> userRepository.delete(
                User.builder()
                .id(id)
                .build()
            )
        );
    }
}
