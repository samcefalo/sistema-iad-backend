package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.dtos.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.repositories.TecnicoRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.TecnicoValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class TecnicoValidator implements ConstraintValidator<TecnicoValid, TecnicoDTO> {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void initialize(TecnicoValid ann) {
    }

    @Override
    public boolean isValid(TecnicoDTO tecnico, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Tecnico existente
        if (tecnico == null || tecnico.getId() == 0
                || !tecnicoRepository.findById(tecnico.getId()).isPresent()) {
            list.add(new FieldMessage("tecnico", "Técnico inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
