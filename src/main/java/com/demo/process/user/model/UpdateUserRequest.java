package com.demo.process.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class UpdateUserRequest {

    @ApiModelProperty(value = "사용자 아이디")
    private UUID id;

    @NotBlank(message = "Required")
    @Length(min = 1, max = 50, message = "At least {min} characters")
    private String username;

}

