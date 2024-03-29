package com.testapi.testapi.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberUniqueConstraintValidator.class)
public @interface NumberUniqueConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
