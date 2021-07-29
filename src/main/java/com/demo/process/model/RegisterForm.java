package com.demo.process.model;

import com.demo.process.validator.AtLeastPassword;
import com.demo.process.validator.Email;
import com.demo.process.validator.PasswordEqualsConfirmPassword;
import com.demo.process.validator.PasswordNotEqualsEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@PasswordEqualsConfirmPassword
@PasswordNotEqualsEmail
public class RegisterForm implements PasswordEqualsConfirmPassword.Model, PasswordNotEqualsEmail.Model {

    @NotBlank(message = "Required")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Required")
    @AtLeastPassword
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String password;

    @NotBlank(message = "Required")
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String confirmPassword;

}
