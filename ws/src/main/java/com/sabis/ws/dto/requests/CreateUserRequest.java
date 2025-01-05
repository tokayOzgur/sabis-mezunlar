package com.sabis.ws.dto.requests;

import com.sabis.ws.validation.userValidation.UniqueEmail;
import com.sabis.ws.validation.userValidation.UniqueUsername;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "{sabis.constraints.username.notblank}")
    @Size(min = 3, max = 50)
    @UniqueUsername
    private String username;

    @NotBlank(message = "{sabis.constraints.email.notblank}")
    @Email(message = "{sabis.constraints.email.invalid}")
    @UniqueEmail
    private String email;

    @Size(min = 8, max = 50, message = "{sabis.constraints.password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+]{8,}$", message = "{sabis.constraints.password.pattern}")
    private String password;
}
