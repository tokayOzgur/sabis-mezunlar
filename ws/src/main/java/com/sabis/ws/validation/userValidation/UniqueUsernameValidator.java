package com.sabis.ws.validation.userValidation;

import com.sabis.ws.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.findByUsername(value).isPresent();
    }
}
