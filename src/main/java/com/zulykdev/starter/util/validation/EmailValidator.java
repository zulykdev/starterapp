package com.zulykdev.starter.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Value("${user.email.regex}")
    private String emailRegex;

    private Pattern pattern;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (emailRegex == null || value == null) return false;
        if (pattern == null) pattern = Pattern.compile(emailRegex);
        return pattern.matcher(value).matches();
    }
}
