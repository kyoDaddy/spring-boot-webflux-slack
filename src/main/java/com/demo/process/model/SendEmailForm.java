package com.demo.process.model;

import com.demo.process.validator.AtLeastPassword;
import com.demo.process.validator.Email;
import com.demo.process.validator.PasswordEqualsConfirmPassword;
import com.demo.process.validator.PasswordNotEqualsEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class SendEmailForm {

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
