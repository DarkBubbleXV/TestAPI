package com.testapi.testapi.validator;

import com.testapi.testapi.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberUniqueConstraintValidator implements ConstraintValidator<NumberUniqueConstraint, String> {
    @Autowired
    private MemberRepo memberRepo;

    @Override
    public boolean isValid(String memberNumber, ConstraintValidatorContext context) {
        return !memberRepo.existsMemberByMemberNumber(memberNumber);
    }
}
