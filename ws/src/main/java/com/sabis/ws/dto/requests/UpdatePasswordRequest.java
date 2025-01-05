package com.sabis.ws.dto.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @Size(min = 8, max = 50, message = "{sabis.constraints.password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+]{8,}$", message = "{sabis.constraints.password.pattern}")
    private String password;

}
