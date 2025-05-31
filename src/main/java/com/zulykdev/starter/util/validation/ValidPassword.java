package com.zulykdev.starter.util.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.zulykdev.starter.util.Constants.INVALID_PASSWORD_MESSAGE;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPassword {
    String message() default INVALID_PASSWORD_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}