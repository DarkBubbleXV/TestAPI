package com.testapi.testapi.validator;

import com.testapi.testapi.dto.MemberRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordEqualConstraintValidator implements ConstraintValidator<PasswordEqualConstrain, Object> {

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext context) {
        MemberRequest memberRequest = (MemberRequest) data;
        return memberRequest.getMemberPassword().equals(memberRequest.getRetypeMemberPassword());
    }
}
