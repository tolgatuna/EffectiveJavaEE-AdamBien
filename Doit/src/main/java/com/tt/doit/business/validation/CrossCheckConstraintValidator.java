package com.tt.doit.business.validation;

import com.tt.doit.business.ValidEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CrossCheckConstraintValidator implements ConstraintValidator<CrossCheck, ValidEntity> {
    @Override
    public void initialize(CrossCheck crossCheck) {

    }

    @Override
    public boolean isValid(ValidEntity validEntity, ConstraintValidatorContext constraintValidatorContext) {
        return validEntity.isValid();
    }
}
