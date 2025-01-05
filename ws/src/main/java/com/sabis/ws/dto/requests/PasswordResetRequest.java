package com.sabis.ws.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordResetRequest {
    @NotNull
    @NotEmpty
    @NotBlank(message = "{sabis.constraints.email.notblank}")
    @Email(message = "{sabis.constraints.email.invalid}")
    private String email;
}
