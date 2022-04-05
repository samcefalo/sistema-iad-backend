package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.EquipeValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class ListEquipeValidator implements ConstraintValidator<EquipeValid, Set<EquipeDTO>> {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void initialize(EquipeValid ann) {
    }

    @Override
    public boolean isValid(Set<EquipeDTO> equipes, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        //tamanho da lista
        if (equipes.size() != 2) {
            list.add(new FieldMessage("equipes", "Insira 2 equipes."));
        }

        //Equipe existente
        for (EquipeDTO equipe : equipes) {
            if (equipe == null || equipe.getId() == 0) {
                list.add(new FieldMessage("equipes", "Equipe inválida."));
            } else if (!equipeRepository.findById(equipe.getId()).isPresent()) {
                list.add(new FieldMessage("equipes", "Equipe " + equipe.getId() + " inválida."));
            }
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}