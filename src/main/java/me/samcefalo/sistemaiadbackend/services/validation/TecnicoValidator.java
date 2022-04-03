package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.repositories.TecnicoRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class TecnicoValidator implements ConstraintValidator<Tecnico, Integer> {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void initialize(Tecnico ann) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Equipe existente
        if (!tecnicoRepository.findById(id).isPresent()) {
            list.add(new FieldMessage("tecnicoId", "Técnico " + id + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
