package br.org.jpositional;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Leonardo Dias de Oliveira
 */

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface BodyPosition {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String identify();
}