package com.testapi.testapi.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordEqualConstraintValidator.class)
public @interface PasswordEqualConstrain {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
