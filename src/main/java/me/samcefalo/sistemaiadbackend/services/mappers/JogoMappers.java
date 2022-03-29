package me.samcefalo.sistemaiadbackend.services.mappers;

import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.JogoFutebol;
import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutebolDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutsalDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JogoMappers {

    //Jogo
    JogoFutsal jogoFutsalDtoToJogoFutsal(JogoFutsalDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutsalDtoToJogoFutsal")
    JogoFutsalDTO jogoFutsalToJogoFutsalDto(JogoFutsal jogoFutsal);

    JogoFutebol jogoFutebolDtoToJogoFutebol(JogoFutebolDTO jogoFutebolDTO);

    @InheritInverseConfiguration(name = "jogoFutebolDtoToJogoFutebol")
    JogoFutebolDTO jogoFutebolToJogoFutebolDto(JogoFutebol jogoFutebol);

    default JogoDTO getJogoDto(Jogo jogo) {
        if (jogo instanceof JogoFutsal) {
            return jogoFutsalToJogoFutsalDto((JogoFutsal) jogo);
        } else {
            return jogoFutebolToJogoFutebolDto((JogoFutebol) jogo);
        }
    }

    default List<JogoDTO> getListJogoDto(List<Jogo> jogos) {
        List<JogoDTO> jogosDtos = new ArrayList<>();
        jogosDtos.addAll(jogos.stream().map(acao -> getJogoDto(acao))
                .collect(Collectors.toList()));
        return jogosDtos;
    }
}
