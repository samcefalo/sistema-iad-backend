package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.objects.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TeamListValidator implements ConstraintValidator<Team, Set<EquipeDTO>> {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void initialize(Team ann) {
    }

    @Override
    public boolean isValid(Set<EquipeDTO> equipes, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        //evitar id duplicado
        Set<Integer> equipeIds = new HashSet<>(equipes.stream().mapToInt(EquipeDTO::getId).boxed().collect(Collectors.toList()));
        //tamanho da lista
        if (equipeIds.size() != 2 || equipes.size() != 2) {
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