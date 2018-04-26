package br.org.jpositional.annotation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Leonardo Dias de Oliveira
 */

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface ComplexPositional {
}
