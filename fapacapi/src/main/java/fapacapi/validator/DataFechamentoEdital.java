package fapacapi.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define a anotação para ser aplicada a campos individuais
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataFechamentoEditalValidator.class)
public @interface DataFechamentoEdital {
    String message() default "A data de fechamento do edital deve ser posterior à data de abertura do edital.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
