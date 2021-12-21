package com.demo.process.user.repository.mapper;

import com.demo.process.base.model.RegisterRequest;
import com.demo.process.base.model.RegisterResponse;
import com.demo.process.user.model.UserResponse;
import com.demo.process.user.repository.entity.User;
import org.mapstruct.Mapper;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * MapStruct
 *  - 객체의 타입 변환 시에 유용하게 사용할 수 있는 라이브러리
 *  - @Mapper 가 붙은 인터페이스는 MapStruct Code Generator가 해당 인터페이스의 구현체를 생성
 *      - 매핑될 속성명이 다를 경우 @Mapping 어노테이션을 통해 매핑정보를 맞춰야 함
 *      - Dependency Injection 사용 가능
 *
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    default RegisterResponse userToRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


    default User registerRequestToUser(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }


    default UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


}
