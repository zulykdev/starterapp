package com.zulykdev.starter.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${user.password.regex}")
    private String passwordRegex;

    private Pattern pattern;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || passwordRegex == null) {
            return false;
        }
        if (pattern == null) {
            pattern = Pattern.compile(passwordRegex);
        }
        return pattern.matcher(password).matches();
    }
}

