package com.sabis.ws.validation.userValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "{sabis.constraints.username.notunique}";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
