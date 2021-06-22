package com.cgi.api.web.annotation;

import com.cgi.api.web.validator.FileTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileTypeValidator.class})
public @interface ValidFileType {

    String message() default "{Invalid file format}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
