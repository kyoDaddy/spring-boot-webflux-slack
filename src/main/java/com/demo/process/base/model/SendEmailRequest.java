package com.demo.process.base.model;

import com.demo.process.login.validator.Email;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class SendEmailRequest {

    @NotBlank(message = "Required")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Required")
    @Length(min = 1, max = 100, message = "At least {min} characters")
    private String subject;

    @NotBlank(message = "Required")
    @Length(min = 1, max = 1000, message = "Must between {min} to {max} characters")
    private String body;

}
