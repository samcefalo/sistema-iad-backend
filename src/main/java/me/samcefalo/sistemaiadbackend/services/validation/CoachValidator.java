package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.dtos.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.repositories.TecnicoRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoachValidator implements ConstraintValidator<Coach, TecnicoDTO> {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void initialize(Coach ann) {
    }

    @Override
    public boolean isValid(TecnicoDTO tecnico, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Tecnico existente
        if (tecnico == null || tecnico.getId() == 0) {
            list.add(new FieldMessage("tecnico", "Técnico inválido."));
        } else if (!tecnicoRepository.findById(tecnico.getId()).isPresent()) {
            list.add(new FieldMessage("tecnico", "Técnico " + tecnico.getId() + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
