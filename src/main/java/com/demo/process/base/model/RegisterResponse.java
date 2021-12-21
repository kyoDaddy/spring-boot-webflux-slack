package com.demo.process.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RegisterResponse {

    @ApiModelProperty(value = "id 사용자")
    private UUID id;

    @ApiModelProperty(value = "사용자 이름")
    private String email;

    @ApiModelProperty(value = "사용자 계정")
    private String username;

    @Builder
    public RegisterResponse(UUID id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
