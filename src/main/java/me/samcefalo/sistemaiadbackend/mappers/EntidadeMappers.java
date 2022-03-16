package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Tecnico;
import me.samcefalo.sistemaiadbackend.domain.dto.JogadorDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.TecnicoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntidadeMappers {

    //Entidade
    @Mapping(target = "equipe.id", source = "equipeId")
    Jogador jogadorDtoToJogador(JogadorDTO jogadorDTO);

    @InheritInverseConfiguration(name = "jogadorDtoToJogador")
    JogadorDTO jogadorToJogadorDto(Jogador jogador);

    @Mapping(target = "equipe.id", source = "equipeId")
    Tecnico tecnicoDtoToTecnico(TecnicoDTO tecnicoDTO);

    @InheritInverseConfiguration(name = "tecnicoDtoToTecnico")
    TecnicoDTO tecnicoToTecnicoDto(Tecnico tecnico);

}
