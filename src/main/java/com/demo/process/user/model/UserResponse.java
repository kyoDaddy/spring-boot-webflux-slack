package com.demo.process.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {

    @ApiModelProperty(value = "id 사용자")
    private UUID id;

    @ApiModelProperty(value = "사용자 이름")
    private String email;

    @ApiModelProperty(value = "사용자 계정")
    private String username;


}
