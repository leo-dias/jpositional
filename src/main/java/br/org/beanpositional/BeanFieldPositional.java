package br.org.beanpositional;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface BeanFieldPositional {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String type();
}