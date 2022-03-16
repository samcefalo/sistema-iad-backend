package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.dto.EquipeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipeMappers {

    //Equipe
    Equipe equipeDtoToEquipe(EquipeDTO equipeDTO);

    @InheritInverseConfiguration(name = "equipeDtoToEquipe")
    EquipeDTO equipeToEquipeDto(Equipe equipe);

}
