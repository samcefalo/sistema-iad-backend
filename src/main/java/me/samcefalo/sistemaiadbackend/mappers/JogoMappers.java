package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.domain.JogoFutebol;
import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutebolDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutsalDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JogoMappers {

    //Jogo
    @Mapping(target = "equipe1.id", source = "equipe1Id")
    @Mapping(target = "equipe2.id", source = "equipe2Id")
    JogoFutsal jogoFutsalDtoToJogoFutsal(JogoFutsalDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutsalDtoToJogoFutsal")
    JogoFutsalDTO jogoFutsalToJogoFutsalDto(JogoFutsal jogoFutsal);

    @Mapping(target = "equipe1.id", source = "equipe1Id")
    @Mapping(target = "equipe2.id", source = "equipe2Id")
    JogoFutebol jogoFutebolDtoToJogoFutebol(JogoFutebolDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutebolDtoToJogoFutebol")
    JogoFutebolDTO jogoFutebolToJogoFutebolDto(JogoFutebol jogoFutebol);

}
