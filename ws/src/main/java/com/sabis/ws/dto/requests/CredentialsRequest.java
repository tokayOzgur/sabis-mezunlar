package com.sabis.ws.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CredentialsRequest(@Email String email, @NotBlank String password) {
}
