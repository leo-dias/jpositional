package br.org.jpositional.annotation;

import br.org.jpositional.annotation.domain.Direction;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Leonardo Dias de Oliveira
 */

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface Line {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int begin();

    int end();

    String fill() default "";

    Direction direction() default Direction.RIGHT;
}