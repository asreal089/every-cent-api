package com.ever.cent.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ever.cent.domain.dto.SignUpRequest;
 
public class PasswordMatchesValidator implements ConstraintValidator<PasswordValidation, SignUpRequest> {
 
    @Override
    public boolean isValid(final SignUpRequest user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}