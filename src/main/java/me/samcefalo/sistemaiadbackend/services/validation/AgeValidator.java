package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.resources.exceptions.objects.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    @Override
    public void initialize(Age ann) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (localDate == null) {
            list.add(new FieldMessage("localDate", "A data de nascimento não pode ser nula."));
        }

        if (localDate != null && localDate.isAfter(LocalDate.now())) {
            list.add(new FieldMessage("localDate", "A data de nascimento não pode ser futura."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
