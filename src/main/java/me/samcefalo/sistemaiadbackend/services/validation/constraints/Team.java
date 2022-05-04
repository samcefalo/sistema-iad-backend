package me.samcefalo.sistemaiadbackend.services.validation.constraints;

import me.samcefalo.sistemaiadbackend.services.validation.TeamListValidator;
import me.samcefalo.sistemaiadbackend.services.validation.TeamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {TeamValidator.class, TeamListValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Team {
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
