package me.samcefalo.sistemaiadbackend.services.mappers;

import me.samcefalo.sistemaiadbackend.domain.JogoFutebol;
import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutebolDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutsalDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JogoMappers {

    //Jogo
    JogoFutsal jogoFutsalDtoToJogoFutsal(JogoFutsalDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutsalDtoToJogoFutsal")
    JogoFutsalDTO jogoFutsalToJogoFutsalDto(JogoFutsal jogoFutsal);

    JogoFutebol jogoFutebolDtoToJogoFutebol(JogoFutebolDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutebolDtoToJogoFutebol")
    JogoFutebolDTO jogoFutebolToJogoFutebolDto(JogoFutebol jogoFutebol);

}
